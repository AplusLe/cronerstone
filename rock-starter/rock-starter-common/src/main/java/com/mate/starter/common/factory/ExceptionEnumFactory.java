package com.mate.starter.common.factory;


import com.mate.starter.common.annotation.ExceptionEnum;

/**
 * 异常枚举code工厂类
 *
 * @author kevin
 */
public class ExceptionEnumFactory {

	public static Integer getExceptionEnum(Class<?> clazz, int code) {

		// 默认的异常响应码
		Integer defaultCode = Integer.valueOf("" + 99 + 9999 + 9);

		if (clazz == null) {
			return defaultCode;
		} else {
			ExceptionEnum exceptionEnum = clazz.getAnnotation(ExceptionEnum.class);
			if (exceptionEnum == null) {
				return defaultCode;
			}
			return Integer.valueOf("" + exceptionEnum.module() + exceptionEnum.kind() + code);
		}

	}
}
