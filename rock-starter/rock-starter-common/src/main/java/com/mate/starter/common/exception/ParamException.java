package com.mate.starter.common.exception;

public class ParamException extends BaseException{

    public ParamException(Integer code,String message){
        super(code,message);
    }

    public ParamException(String message,Integer code){
        super(code,message);
    }

    public ParamException(String message) {
        super(message);
    }
}
