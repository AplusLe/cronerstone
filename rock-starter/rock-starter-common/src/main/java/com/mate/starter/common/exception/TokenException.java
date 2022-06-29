package com.mate.starter.common.exception;


import com.mate.starter.common.enums.BaseExceptionEnum;

/**
 * Token处理异常
 *
 * @author xuzhanfu
 */
public class TokenException extends BaseException {

	private static final long serialVersionUID = -109638013567525177L;

	public TokenException(String message) {
		super(message);
	}
	public TokenException(BaseExceptionEnum exception) {
		super(exception);
	}
}
