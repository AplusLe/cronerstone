package com.mate.starter.common.enums;

import com.mate.starter.common.annotation.ExceptionEnum;
import com.mate.starter.common.constant.MateExceptionConstant;
import com.mate.starter.common.enums.BaseExceptionEnum;
import com.mate.starter.common.factory.ExceptionEnumFactory;
import lombok.AllArgsConstructor;

/**
 * 通用异常枚举类
 *
 * @author kevin
 */
@AllArgsConstructor
@ExceptionEnum(module = MateExceptionConstant.MATE_COMMON_MODULE, kind = MateExceptionConstant.MATE_COMMON_ENUM)
public enum MateExceptionEnum implements BaseExceptionEnum {
	/**
	 * 通用异常
	 */
	SERVER_ERROR(1, "服务器异常,请联系系统运维人员"),
	NO_THIS_USER(2, "没有此用户"),
	USER_AUTH_FAIL(3, "用户认证失败"),
	NULL_POINT_EXCEPTION(4, "获取数据为空"),
	TOKEN_HAS_EXPIRED(5, "登录已失效，请重新登录"),
	TOKEN_DOES_NOT_EXIST(6, "未携带TOKEN信息"),
	PRE_AUTH_NOT_PRIVILEGE(7, "您没有此权限");

	private final Integer code;

	private final String message;

	@Override
	public Integer getCode() {
		return ExceptionEnumFactory.getExceptionEnum(this.getClass(), code);
	}

	@Override
	public String getMessage() {
		return message;
	}
}
