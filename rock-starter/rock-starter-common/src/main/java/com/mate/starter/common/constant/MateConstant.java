package com.mate.starter.common.constant;

import lombok.experimental.UtilityClass;

/**
 * 基础常量
 *
 * @author pangu
 */
@UtilityClass
public class MateConstant {

    /**
     * 应用版本号
     */
    public final String MATE_APP_VERSION = "1.0.0";

    /**
     * 前端工程名
     */
    public final String FRONT_END_PROJECT = "a-plus";

    /**
     * 后端工程名
     */
    public final String BACK_END_PROJECT = "rock";

    /**
     * Spring 应用名 prop key
     */
    public final String SPRING_APP_NAME_KEY = "spring.application.name";

    public final String CONTEXT_KEY = "mateContext";

    public final String MDC_MATE_TRACE_ID = "traceId";

    public final String MDC_MATE_USER_ID = "userId";

    public final String MDC_MATE_USER_NAME = "userName";

    public final String MDC_MATE_TENANT_ID = "tenantId";
    /**
     * 默认为空消息
     */
    public final String DEFAULT_NULL_MESSAGE = "承载数据为空";
    /**
     * 默认成功消息
     */
    public final String DEFAULT_SUCCESS_MESSAGE = "处理成功";
    /**
     * 默认失败消息
     */
    public final String DEFAULT_FAIL_MESSAGE = "处理失败";
    /**
     * 树的根节点值
     */
    public final Long TREE_ROOT = -1L;
    /**
     * 允许的文件类型，可根据需求添加
     */
    public final String[] VALID_FILE_TYPE = {"xlsx", "zip"};

    public final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 微服务之间传递的唯一标识
     */
    public final String MATE_TRACE_ID = "mate-trace-id";

    /**
     * 日志链路追踪id日志标志
     */
    public final String LOG_TRACE_ID = "traceId";

    /**
     * 微服务之间传递的用户ID
     */
    public final String MATE_MICRO_USER_ID = "mate-user-id";

    /**
     * 微服务之间传递的租户
     */
    public final String MATE_MICRO_TENANT_ID = "mate-tenant-id";

    /**
     * 微服务之间传递的租户
     */
    public final String MATE_MICRO_USER_NAME = "mate-user-name";

    /**
     * Java默认临时目录
     */
    public final String JAVA_TEMP_DIR = "java.io.tmpdir";

    /**
     * 版本
     */
    public final String VERSION = "version";

    /**
     * 默认版本号
     */
    public final String DEFAULT_VERSION = "v1";

    /**
     * 服务资源
     */
    public final String MATE_SERVICE_RESOURCE = "mate-service-resource";

    /**
     * API资源
     */
    public final String MATE_API_RESOURCE = "mate-api-resource";

    /**
     * 权限认证的排序
     */
    public final int MATE_UAA_FILTER_ORDER = -200;

    /**
     * 签名排序
     */
    public final int MATE_SIGN_FILTER_ORDER = -300;

    /**
     * json类型报文，UTF-8字符集
     */
    public final String JSON_UTF8 = "application/json;charset=UTF-8";

    /**
     * 未知标识
     */
    public final String UNKNOWN = "Unknown";

    /**
     * 用户代理
     */
    public final String USER_AGENT = "User-Agent";

    /**
     * 用户ID编码
     */
    public final String MATE_USER_ID = "userId";

    /**
     * 用户名
     */
    public final String MATE_USER_NAME = "userName";

    /**
     * 姓名
     */
    public final String MATE_NAME = "name";

    /**
     * 租户ID
     */
    public final String MATE_TENANT_ID = "tenantId";

    /**
     * 企业ID
     */
    public final String MATE_ORG_ID = "orgId";

    /**
     * 前端密码加密串
     */
    public final String PASSWORD_SECURITY_KEY = "===Mate--Auth===";

    public final Integer COMMON_STATUS_CODE = 500;

    /**
     * 动态路由文件名
     */
    public final String NACOS_ROUTES_NAME = "mate-routes.yaml";

    /**
     * 动态路由超时时间,时间毫秒
     */
    public static final long NACOS_ROUTES_TIMEOUT = 5000;

}
