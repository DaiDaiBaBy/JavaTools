package com.zhoufu.springbootswagger2.common;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
/**
 * @Author: ZhouFu
 * @Date: 2021/7/7 16:02
 * @description:  统一结果封装
 */
/**
 *  是否成功，可用code表示，(如200表示成功， 400表示异常)
 *  结果消息
 *  结果数据
 */
@Data
@Getter
public class Result<T> implements Serializable {

    private int code;

    private String message;

    private long timestamp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public static<T> Result<T> success(T data) {
        return success(data, ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data,String message){
        Result<T> result = new Result<>();
        result.setResultEnum(ResultEnum.SUCCESS);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(ResultEnum resultEnum, String message) {
        Result<T> result = new Result<T>();
        result.setResultEnum(resultEnum);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> failure(ResultEnum resultEnum) {
        Result<T> result = new Result<T>();
        result.setResultEnum(resultEnum);
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    public static <T> Result<T> failure(String error) {
        Result<T> result = new Result<T>();
        result.setCode(ResultEnum.FAILURE.getCode());
        result.setMessage(error);
        return result;
    }

    public static <T> Result<T> failure(ResultEnum resultEnum, String message) {
        Result<T> result = new Result<T>();
        result.setResultEnum(resultEnum);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> failure(Integer code, String message) {
        Result<T> result = new Result<T>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


    public void setResultEnum(ResultEnum code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

}

