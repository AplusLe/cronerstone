package com.mate.starter.datasource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数据源配置类型
 * @author Kevin
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum DsConfTypeEnum {
	/**
	 * 主机链接
	 */
	HOST(0, "主机链接"),

	/**
	 * JDBC链接
	 */
	JDBC(1, "JDBC链接");

	private final Integer type;

	private final String description;
}
