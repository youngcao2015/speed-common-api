package com.speed.plugin.token;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.speed.common.anno.TokenSecurity;
/**
 * Token验证
 * @author Administrator
 *
 */
@Aspect
@Component
public class SecurityAspect {
	
	private static final String DEFAULT_TOKEN_NAME = "X-Token";

	@Resource(name="redisTokenManager") private TokenManager tokenManager;
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void controllerAspect() {
		
	}
	
	/**
	 * 接收到客户端请求时执行
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerAspect()")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		// 从切点上获取目标方法
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();
		/**
		 * 验证Token
		 */
		if (method.isAnnotationPresent(TokenSecurity.class)) {
			// 从 request header 中获取当前 token
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			String token = 	request.getHeader(DEFAULT_TOKEN_NAME);
			if(StringUtils.isEmpty(token)){
				throw new TokenException("客户端X-Token参数不能为空,且从Header中传入,如果没有登录,请先登录获取Token");
			}
			// 检查 token 有效性
			if (!tokenManager.checkToken(token)) {
				String message = String.format("Token [%s] 非法", token);
				throw new TokenException(message);
			}
		}
		
		// 调用目标方法
		return pjp.proceed();
	}
}
