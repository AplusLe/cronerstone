package com.mate.starter.web.interceptor;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.mate.starter.common.constant.MateConstant;
import com.mate.starter.common.constant.Oauth2Constant;
import com.mate.starter.common.constant.pool.StringPool;
import com.mate.starter.common.context.MateContextHolder;
import com.mate.starter.common.entity.LoginUser;
import com.mate.starter.common.ttl.ThreadLocalUtil;
import com.mate.starter.common.utils.ObjectUtil;
import com.mate.starter.common.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mate.starter.web.utils.WebUtil.getHeader;

/**
 * 拦截器：
 * 将请求头数据，封装到BaseContextHandler(ThreadLocal)
 * <p>
 * 该拦截器要优先于系统中其他的业务拦截器
 * <p>
 *
 * @author pangu
 */
@Slf4j
public class HeaderThreadLocalInterceptor implements HandlerInterceptor {

    @Value("${mate.tenant.enable}")
    private boolean isTenantId = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        LoginUser user = null;
        if (StringUtils.hasText(getHeader(request, Oauth2Constant.HEADER_TOKEN))) {
            user = SecurityUtil.getUsername(request);
        }
        // 通过feign传值的时候，需要从header获取
        String userId = getHeader(request, MateConstant.MATE_USER_ID);
        String userName = getHeader(request, MateConstant.MATE_USER_NAME);
        String name = getHeader(request, MateConstant.MATE_NAME);
        String orgId = getHeader(request, MateConstant.MATE_ORG_ID);
        // 如果是token传值的时候，则从token解析出来的user对象获取
        if (user != null) {
            userId = ObjectUtil.toStr(user.getId());
            userName = user.getAccount();
            name = user.getName();
        }
        // 设置ThreadLocal变量
        if (isTenantId) {
            MateContextHolder.setTenantId(getHeader(request, MateConstant.MATE_MICRO_TENANT_ID));
        }
        // 给MateContextHolder赋值
        MateContextHolder.setUserId(StringUtils.hasText(userId) ?  ObjectUtil.toLong(userId) : 0L);
        MateContextHolder.setUserName(StringUtils.hasText(userName) ? userName : StringPool.EMPTY);
        MateContextHolder.setName(StringUtils.hasText(name) ? name : StringPool.EMPTY);
        MateContextHolder.setOrgId(StringUtils.hasText(orgId) ? ObjectUtil.toLong(orgId) : 0L);

        String traceId = request.getHeader(MateConstant.MATE_TRACE_ID);
        MDC.put(MateConstant.LOG_TRACE_ID, StrUtil.isEmpty(traceId) ? IdUtil.fastSimpleUUID() : traceId);
        MDC.put(MateConstant.MATE_TENANT_ID, MateContextHolder.getTenantId());
        MDC.put(MateConstant.MATE_USER_ID, MateContextHolder.getUserId().toString());
        MDC.put(MateConstant.MATE_USER_NAME, MateContextHolder.getUserName());
        MDC.put(MateConstant.MATE_NAME, MateContextHolder.getName());
        MDC.put(MateConstant.MATE_ORG_ID, MateContextHolder.getOrgId().toString());
        response.setHeader(MateConstant.MATE_MICRO_TENANT_ID, MateContextHolder.getTenantId());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
