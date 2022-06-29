package com.mate.starter.common.exception;

import com.mate.starter.common.api.IResultCode;
import com.mate.starter.common.enums.BaseExceptionEnum;
import com.mate.starter.common.utils.MessageUtil;
import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * 通用异常
 *
 */
@Data
public class BaseException extends RuntimeException {
	private final Integer code;
	private final String message;

	public BaseException(String message) {
		super(message);
		this.message = message;
		code =  500;
	}

	/**
	 * 根据错误信息构建异常
	 *
	 * @param code      错误码
	 * @param message  错误信息
	 */
	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseException(HttpStatus status, String message) {
		super(message);
		this.code = status.value();
		this.message = message;
	}
	public BaseException(BaseExceptionEnum exception) {
		super(exception.getMessage());
		this.code = exception.getCode();
		this.message = exception.getMessage();
	}



	/**
	 * 根据错误信息和异常栈构建业务异常对象
	 *
	 * @param message  错误信息
	 * @param throwable 异常对象
	 */
	public BaseException(String message, Throwable throwable) {
		super(message, throwable);
		this.message = message;
		code =  500;
	}

	/**
	 * 根据错误信息和错误参数构建异常
	 *
	 * @param code      错误码
	 * @param message  错误信息
	 * @param args      拼接参数
	 */
	public BaseException(Integer code, String message, Object... args) {
		super(String.format(message, args));
		this.code = code;
		this.message = String.format(message, args);
	}

	/**
	 * 根据错误码信息和拼接参数构建异常
	 *
	 * @param error     错误码枚举
	 * @param args      拼接参数
	 */
	public <E extends Enum<?> & IResultCode> BaseException(E error, Object... args) {
		super(String.format(error.getMsg(), args));
		this.code = error.getCode();
		this.message = MessageUtil.format(error.getMsg(), args);
	}

	/**
	 * 根据错误码信息构建异常
	 *
	 * @param error     错误码枚举
	 */
	public <E extends Enum<?> & IResultCode> BaseException(E error) {
		super(error.getMsg());
		this.code = error.getCode();
		this.message = error.getMsg();
	}

	/**
	 * 根据错误码信息构建异常
	 *
	 * @param error     错误码枚举
	 * @param throwable 异常栈
	 */
	public <E extends Enum<?> & IResultCode> BaseException(E error, Throwable throwable) {
		super(error.getMsg(), throwable);
		this.code = error.getCode();
		this.message = error.getMsg();
	}

}
