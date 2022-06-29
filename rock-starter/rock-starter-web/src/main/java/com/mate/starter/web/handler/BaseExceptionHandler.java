package com.mate.starter.web.handler;

import com.mate.starter.common.api.Result;
import com.mate.starter.common.api.ResultCode;
import com.mate.starter.common.exception.BaseException;
import com.mate.starter.common.exception.ParamException;
import com.mate.starter.common.exception.TokenException;
import com.mate.starter.common.exception.TransException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * WEB应用全局异常处理
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class BaseExceptionHandler {

    /**
     * BaseException 异常捕获处理
     * @param ex 自定义BaseException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(BaseException ex) {
        log.error("程序异常：{}" ,ex.toString());
        return Result.fail(ResultCode.FAILURE.getCode(), ex.getMessage());
    }

    /**
     * TokenException 异常捕获处理
     * @param ex 自定义TokenException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(TokenException ex) {
        log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return Result.fail(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    /**
     * FileNotFoundException,NoHandlerFoundException 异常捕获处理
     * @param exception 自定义FileNotFoundException异常类型
     * @return Result
     */
    @ExceptionHandler({FileNotFoundException.class, NoHandlerFoundException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result<?> noFoundException(Exception exception) {
        log.error("程序异常==>errorCode:{}, exception:{}", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return Result.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }


    /**
     * NullPointerException 空指针异常捕获处理
     * @param ex 自定义NullPointerException异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(NullPointerException ex) {
        log.error("程序异常：{}" , ex.toString());
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(HttpRequestMethodNotSupportedException ex) {
        log.error("请求方式错误：{}" , ex.toString());
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求方式错误");
    }

    /**
     * 通用Exception异常捕获
     * @param ex 自定义Exception异常类型
     * @return Result
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(Exception ex) {
        log.error("程序异常：{}",  ex.toString());
        if(ex instanceof MissingServletRequestParameterException){
            return Result.fail(ResultCode.MISSING_PARAM_ERROR.getCode(), ResultCode.MISSING_PARAM_ERROR.getMsg());
        }
        return Result.fail(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg());
    }


    /**
     * @Valid的校验异常返回信息
     * @param ex
     * {
     *     "code": 4000,
     *     "msg": "openId不能为空;appId不能为空",
     *     "time": 1636097032237,
     *     "success": false
     * }
     * @return
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(MethodArgumentNotValidException ex) {
        log.error("参数校验异常：{}" , ex.toString());
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(s -> s.getDefaultMessage()).collect(Collectors.joining(";"));
        return Result.fail(ResultCode.GLOBAL_PARAM_ERROR.getCode(), message);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(ParamException ex) {
        log.error("ParamException异常：{}" , ex.toString());
        String message = ex.getMessage();
        return Result.fail(ResultCode.GLOBAL_PARAM_ERROR.getCode(), message);
    }

    @ExceptionHandler(value = TransException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(TransException ex) {
        String message = ex.getMessage();
        log.error("feign调用异常:{}，需要回滚" ,message);
        throw new TransException(message);
    }
}
