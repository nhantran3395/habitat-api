package com.epam.hackathongood.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.epam.hackathongood.interceptors.JWTInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JWTInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/**","/api-docs","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
	}
}
