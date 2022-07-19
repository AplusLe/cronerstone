package com.mate.starter.common.enums;

/**
 * 通用异常枚举类
 *
 * @author kevin
 */
public interface BaseExceptionEnum {
	/**
	 * 异常状态码
	 *
	 * @return int类型
	 */
	Integer getCode();

	/**
	 * 消息
	 *
	 * @return 字符串
	 */
	String getMessage();
}
