package com.speed.plugin.token;
/**
 * Token接口
 * @author Jack.cao
 * @date 2016年12月29日 下午2:34:00
 */
public interface TokenManager {
	
	/**
	 * 生成Tokean
	 */
	String createToken(String uid);
	/**
	 * 检查Token有效性
	 */
	boolean checkToken(String token);
}
