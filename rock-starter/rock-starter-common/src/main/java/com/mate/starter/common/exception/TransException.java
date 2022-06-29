package com.mate.starter.common.exception;

/**
 * 事务异常
 * 针对feign调用，提供方需要回滚的抛出此异常
 * BaseExceptionHandler捕捉到后继续向上抛
 * feign到callback捕捉此异常
 *
 */
public class TransException extends RuntimeException {


	public TransException(String message) {
		super(message);
	}
}
