package com.mate.starter.database.props;//package com.cloudlink.starter.database.props;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 租户属性
// */
//@Getter
//@Setter
//@RefreshScope
//@ConfigurationProperties(prefix = "mate.tenant")
//public class TenantProperties {
//
//    /**
//     * 是否开启租户模式
//     */
//    private Boolean enable = false;
//
//    /**
//     * 需要排除的多租户的表
//     */
//    private List<String> ignoreTables = Arrays.asList("mate_sys_menu","mate_sys_role","mate_sys_role_data_scope","mate_sys_role_menu",
//            "mate_sys_user","mate_sys_user_data_scope","mate_sys_user_role","mate_sys_org","mate_sys_pos","mate_sys_emp",
//            "mate_sys_emp_ext_org_pos","mate_sys_emp_pos","mate_sys_app","mate_sys_dict","mate_sys_client","mate_sys_data_source",
//            "mate_sys_code","mate_sys_code_config","mate_sys_set","mate_sys_material_group","mate_sys_material","mate_sys_api",
//            "mate_sys_blacklist","mate_sys_rate_limit","mate_sys_route","mate_sys_tenant","mate_sys_notice","mate_sys_notice_user",
//            "mate_sys_sms","mate_sys_log","mate_sys_login_log","mate_app_user","mate_i18n_data");
//
//    /**
//     * 多租户字段名称
//     */
//    private String column = "tenant_id";
//}
