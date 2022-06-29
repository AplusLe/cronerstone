package com.mate.starter.common.exception;

import com.mate.starter.common.enums.BaseExceptionEnum;

/**
 * 通用异常类
 *
 * @author pangu
 */
public class MateException extends BaseException {

	private static final long serialVersionUID = -5737014446398630986L;

	public MateException(String message) {
		super(message);
	}

	public MateException(Integer code,String message) {
		super(code,message);
	}


	public MateException(BaseExceptionEnum exception) {
		super(exception);
	}
}
