package com.zhoufu.springbootswagger2.common;

import java.io.Serializable;

public enum ResultEnum implements Serializable {

    SUCCESS(200, "请求成功"),
    FAILURE(-1, "请求失败"),
    SERVICE(-2,"系统未知的错误"),

    ;

    private int code;
    private String message;


    private ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}