package com.zhoufu.springbootdynamictask.common;

/**
 * @ClassName : EnumTaskEnable
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:50
 * @Description : 任务状态枚举类
 */
public enum EnumTaskEnable {
    START("2", "开启"),
    STOP("0", "关闭");

    private String code;

    private String msg;

    EnumTaskEnable(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
}