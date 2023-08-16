package com.zhoufu.springbootmockito.common;

import lombok.Data;
/**
 * @ClassName : ResponseVO
 * @Author : ZhouFu
 * @Date: 2023/8/16 16:36
 * @Description : 统一返回对象
 */
@Data
public class ResponseVO<T> {
    private T data;
    private String msg;
    private String code;

    static class ResponseCode{
        private static String SUCCESS = "000000";
        private static String FAIL = "100000";
        private static String SYSTEM_EXCEPTION = "100500";
    }

    public ResponseVO(){}
    public ResponseVO(T data, String msg, String code){
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    /**
     * 返回httpstatus：200
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseVO<T> ok(String msg,T data){
        return new ResponseVO(data,msg, ResponseCode.SUCCESS);
    }
    public static <T> ResponseVO<T> ok(String msg){
        return ok(msg,null);
    }


    public static <T> ResponseVO<T> fail(String msg,T data){
        return new ResponseVO(data,msg, ResponseCode.FAIL);
    }
    public static <T> ResponseVO<T> fail(String msg){
        return fail(msg,null);
    }
}
