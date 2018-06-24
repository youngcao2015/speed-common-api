package com.speed.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 * @author Jack.cao
 * @date 2016年12月29日 下午6:33:43
 */
@EnableWebMvc  
@EnableSwagger2  
@ComponentScan(basePackages = {"com.speed.controller"})
@Configuration  
public class SwaggerConfig  {

	 @Bean  
	    public Docket createRestApi() {  
	        return new Docket(DocumentationType.SWAGGER_2)  
	                .apiInfo(apiInfo())  
	                .select()  
	                .apis(RequestHandlerSelectors.basePackage("com.speed.controller"))
	                .paths(PathSelectors.any())  
	                .build();  
	    }  
	  
	    private ApiInfo apiInfo() {  
	        return new ApiInfoBuilder()  
	                .title("接口文档1.1版")
	                .termsOfServiceUrl("http://www.google.com/")
	                .contact("Jack.cao")
	                .version("1.1")
	                .build();  
	    }  
}
 
