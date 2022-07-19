package com.mate.starter.common.context;

import com.mate.starter.common.constant.Oauth2Constant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Headers配置信息
 *
 * @author kevin
 */
@Getter
@Setter
@ConfigurationProperties(MateContextProperties.PREFIX)
public class MateContextProperties {

	/**
	 * 配置前缀
	 */
	public static final String PREFIX = "mate.context";

	/**
	 * 上下文传递的 headers 信息
	 */
	private Headers headers = new Headers();

	@Getter
	@Setter
	public static class Headers {
		/**
		 * traceId，默认：MATE_TRACE_ID
		 */
		private String traceId = "mate-trace-id";
		/**
		 * 用于 聚合层 向调用层传递用户信息 的请求头，默认：MATE_USER_ID
		 */
		private String userId = "mate-user-id";
		/**
		 * 用于 聚合层 向调用层传递用户名 的请求头，默认：MATE_MICRO_USER_NAME
		 */
		private String userName = "mate-user-name";
		/**
		 * 用于 聚合层 向调用层传递租户id 的请求头，默认：MATE_TENANT_ID
		 */
		private String tenantId = "mate-tenant-id";
		/**
		 * 自定义 RestTemplate 和 Feign 透传到下层的 Headers 名称列表
		 */
		private List<String> allowed = Arrays.asList("X-Real-IP", "x-forwarded-for", "authorization", "Authorization", Oauth2Constant.HEADER_TOKEN.toLowerCase(), Oauth2Constant.HEADER_TOKEN);
	}

	/**
	 * 获取跨服务的请求头
	 *
	 * @return 请求头列表
	 */
	public List<String> getCrossHeaders() {
		List<String> headerList = new ArrayList<>();
		headerList.add(headers.getTenantId());
		headerList.add(headers.getUserId());
		headerList.add(headers.getTenantId());
		headerList.addAll(headers.getAllowed());
		return headerList;
	}
}
