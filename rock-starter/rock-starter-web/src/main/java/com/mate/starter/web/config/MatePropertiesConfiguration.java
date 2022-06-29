package com.mate.starter.web.config;

import com.mate.starter.web.props.MateJacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * mica spring boot props
 *
 * @author kevin
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({
		MateJacksonProperties.class,
})
public class MatePropertiesConfiguration {
}
