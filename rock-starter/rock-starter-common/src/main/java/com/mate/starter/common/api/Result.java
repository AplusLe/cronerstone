package com.mate.starter.common.api;

import cn.hutool.core.util.BooleanUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mate.starter.common.constant.MateConstant;
import com.mate.starter.common.exception.MateException;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 统一响应消息报文
 *
 * @param <T> 　T对象
 * @author kevin
 */
@Data
@Getter
public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;

	private String msg;

	private long time;

	private boolean success;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	private Result() {
		this.time = System.currentTimeMillis();
	}

	private Result(IResultCode resultCode) {
		this(resultCode, null, resultCode.getMsg());
	}

	private Result(IResultCode resultCode, String msg) {
		this(resultCode, null, msg);
	}

	private Result(IResultCode resultCode, T data) {
		this(resultCode, data, resultCode.getMsg());
	}

	private Result(IResultCode resultCode, T data, String msg) {
		this(resultCode.getCode(), data, msg);
	}

	private Result(int code, T data, String msg) {
		this.code = code;
		this.data = data;
		this.msg = msg;
		this.time = System.currentTimeMillis();
		this.success = ResultCode.SUCCESS.code == code;
	}

	/**
	 * 返回状态码
	 *
	 * @param resultCode 状态码
	 * @param <T>        泛型标识
	 * @return ApiResult
	 */
	public static <T> Result<T> success(IResultCode resultCode) {
		return new Result<>(resultCode);
	}

	public static <T> Result<T> success( ) {
		return new Result<>(ResultCode.SUCCESS, "请求成功");
	}
	public static <T> Result<T> success(String msg) {
		return new Result<>(ResultCode.SUCCESS, msg);
	}

	public static <T> Result<T> success(IResultCode resultCode, String msg) {
		return new Result<>(resultCode, msg);
	}

	public static <T> Result<T> data(T data) {
		return data(data, MateConstant.DEFAULT_SUCCESS_MESSAGE);
	}

	public static <T> Result<T> data(T data, String msg) {
		return data(ResultCode.SUCCESS.code, data, msg);
	}

	public static <T> Result<T> data(int code, T data, String msg) {
		return new Result<>(code, data, data == null ? MateConstant.DEFAULT_NULL_MESSAGE : msg);
	}

	public static <T> Result<T> fail() {
		return new Result<>(ResultCode.FAILURE, ResultCode.FAILURE.getMsg());
	}

	public static <T> Result<T> fail(String msg) {
		return new Result<>(ResultCode.FAILURE, msg);
	}

	public static <T> Result<T> fail(int code, String msg) {
		return new Result<>(code, null, msg);
	}

	public static <T> Result<T> fail(IResultCode resultCode) {
		return new Result<>(resultCode);
	}

	public static <T> Result<T> fail(IResultCode resultCode, String msg) {
		return new Result<>(resultCode, msg);
	}

	public static <T> Result<T> condition(boolean flag) {
		return flag ? success(MateConstant.DEFAULT_SUCCESS_MESSAGE) : fail(MateConstant.DEFAULT_FAIL_MESSAGE);
	}

	/**
	 * 处理是否成功
	 *
	 * @return  t-成功
	 *          f-失败
	 */
	public boolean isSuccess() {
		return Objects.equals(this.code, ResultCode.SUCCESS.getCode());
	}

	/**
	 * 请求成功但无返回数组
	 *
	 * @param <T>       返回结果类型
	 * @return          Result
	 */
	public static <T> Result<List<T>> emptyList() {
		return new Result<>(ResultCode.SUCCESS, Collections.emptyList());
	}

	/* 返回结果函数式处理方法  */
	/**
	 * 接收单个对象组成处理结果
	 *
	 * @param data      返回结果
	 * @param condition 成功条件
	 * @return          Result 返回结果类型
	 */
	public static <T, E extends Enum<?> & IResultCode> Result<T> accept(T data, Function<T, E> condition) {
		return Optional.ofNullable(condition)
				// 应用成功条件
				.map(cond -> cond.apply(data))
				// 若返回错误码枚举且不是SUCCESS代表出现异常，返回ERROR，否则返回OK
				.filter(error -> error != ResultCode.SUCCESS)
				.map(Result::<T>fail)
				.orElseGet(() -> Result.data(data));
	}

	/**
	 * 接收单个对象组成处理结果
	 *
	 * @param data      返回结果
	 * @param condition 成功条件
	 * @param <T>       返回结果类型
	 * @return          Result
	 */
	public static <T> Result<T> predicate(T data, Predicate<T> condition) {
		return Optional.ofNullable(condition)
				// 应用成功条件
				//.map(cond -> cond.test(data))
				// 若返回错误返回FAIL，否则返回DATA
				.filter(cond -> BooleanUtil.isFalse(cond.test(data)))
				.map(cond -> Result.<T>fail())
				.orElseGet(() -> Result.data(data));
	}

	/**
	 * 对非空的返回结果对象应用变型
	 *
	 * @param function  map函数
	 * @param <R>       返回值类型
	 * @return          返回结果
	 */
	public <R> Result<R> map(Function<T, R> function) {
		if (this.data != null) {
			this.data = (T) function.apply(this.data);
			return (Result<R>) this;
		}
		return (Result<R>) this;
	}

	/**
	 * 确保非空
	 */
	public Result<T> orElse(Result<T> other) {
		if (null == this.data) {
			return other;
		}
		return this;
	}

	/**
	 * 对非空的返回结果对象应用筛选
	 *
	 * @param function  筛选函数
	 * @return          筛选后的结果
	 */
	public Result<T> filter(Predicate<T> function) {
		if (!function.test(this.data)) {
			this.data = null;
		}
		return this;
	}

	/**
	 * 尝试取出结果封装对象，如果是错误结果会将错误码对象封装到对象中
	 *
	 * @return          Optional
	 */
	public T tryGet(BiConsumer<Integer, String> function) {
		if (!isSuccess()) {
			function.accept(this.code, this.msg);
		}
		return data;
	}

	/**
	 * 尝试取出结果封装对象，如果是错误结果会将错误码对象封装到对象中
	 *
	 * @return          Optional
	 */
	public Optional<T> optionalTryGet(BiConsumer<Integer, String> function) {
		if (!isSuccess()) {
			function.accept(this.code, this.msg);
		}
		return Optional.ofNullable(data);
	}

	/**
	 * 尝试取出结果封装对象，如果是错误结果直接抛出异常
	 *
	 * @return          Optional
	 */
	public T tryGetThrow() {
		return tryGet(
				(errorCode, errorMessage) -> {
					if (!isSuccess()) {
						throw new MateException(errorCode, errorMessage);
					}
				}
		);
	}

}
