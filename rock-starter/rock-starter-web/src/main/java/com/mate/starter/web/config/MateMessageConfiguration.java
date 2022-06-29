package com.mate.starter.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mate.starter.web.jackson.MappingApiJackson2HttpMessageConverter;
import com.mate.starter.web.props.MateJacksonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 消息转换器
 *
 * @author kevin
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MateMessageConfiguration implements WebMvcConfigurer {

	private final MateJacksonProperties properties;
	private final ObjectMapper objectMapper;

	/**
	 * 消息转换，内置断点续传，下载和字符串
	 *
	 * @param converters 转换器
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter);
		converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		converters.add(new MappingApiJackson2HttpMessageConverter(objectMapper, properties));
	}

}
