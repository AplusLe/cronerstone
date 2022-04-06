package com.mate.starter.datasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Druid数据源属性类
 * <p>
 * 参考DruidDataSourceWrapper
 * </p>
 *
 * @author pangu
 * @date 2021-2-3
 */
@Data
@Component
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceProperties {

	/**
	 * 数据源用户名
	 */
	private String username;

	/**
	 * 数据源密码
	 */
	private String password;

	/**
	 * jdbcurl
	 */
	private String url;

	/**
	 * 数据源驱动
	 */
	private String driverClassName;

	/**
	 * 查询数据源的SQL
	 */
	private String queryDsSql = "select * from mate_sys_data_source where status = 0";

}
