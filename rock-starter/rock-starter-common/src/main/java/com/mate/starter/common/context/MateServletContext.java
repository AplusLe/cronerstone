package com.mate.starter.common.context;

import com.mate.starter.common.constant.MateConstant;
import com.mate.starter.common.ttl.ThreadLocalUtil;
import io.micrometer.core.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.function.Function;

/**
 * mate servlet 上下文，跨线程失效
 *
 * @author L.cm
 */
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class MateServletContext implements MateContext {

	private final MateContextProperties contextProperties;
	private final MateHttpHeadersGetter httpHeadersGetter;

	@Nullable
	@Override
	public String getTraceId() {
		return get(contextProperties.getHeaders().getTraceId());
	}

	@Nullable
	@Override
	public String getUserId() {
		return get(contextProperties.getHeaders().getUserId());
	}

	@Nullable
	@Override
	public String getTenantId() {
		return get(contextProperties.getHeaders().getTenantId());
	}

	@Nullable
	@Override
	public String get(String ctxKey) {
		HttpHeaders headers = ThreadLocalUtil.getIfAbsent(MateConstant.CONTEXT_KEY, httpHeadersGetter::get);
		if (headers == null || headers.isEmpty()) {
			return null;
		}
		return headers.getFirst(ctxKey);
	}

	@Override
	public <T> T get(String ctxKey, Function<String, T> function) {
		String ctxValue = get(ctxKey);
		if (StringUtils.hasText(ctxValue)) {
			return function.apply(ctxKey);
		}
		return null;
	}
}
