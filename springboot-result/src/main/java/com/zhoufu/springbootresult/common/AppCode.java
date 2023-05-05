package com.zhoufu.springbootresult.common;

import lombok.Getter;

/**
 * @ClassName : AppCode
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:55
 * @Description : 异常状态码枚举
 */
@Getter
public enum AppCode implements StatusCode{
    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常");
    private int code;
    private String msg;
    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
