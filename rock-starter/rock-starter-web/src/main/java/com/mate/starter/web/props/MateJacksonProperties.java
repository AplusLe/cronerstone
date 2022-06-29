package com.mate.starter.web.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * jackson 配置
 *
 * @author L.cm
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("mate.jackson.response")
public class MateJacksonProperties {

	/**
	 * null 转为 空，字符串转成""，数组转为[]，对象转为{}
	 */
	private Boolean nullToEmpty = Boolean.FALSE;
	/**
	 * 响应到前端，大数值自动写出为 String，避免精度丢失
	 */
	private Boolean bigNumToString = Boolean.TRUE;
	/**
	 * 支持 MediaType text/plain，用于和 mate-api-encrypt 一起使用
	 */
	private Boolean supportTextPlain = Boolean.FALSE;

}
