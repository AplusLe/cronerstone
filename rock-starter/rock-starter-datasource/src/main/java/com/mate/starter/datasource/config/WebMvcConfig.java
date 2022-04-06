package com.mate.starter.datasource.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 *
 * @author pangu
 * @date 2021-3-4
 */
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ClearTtlDsInterceptor()).addPathPatterns("/**");
	}
}
