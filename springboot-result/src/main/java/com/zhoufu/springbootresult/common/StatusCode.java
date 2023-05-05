package com.zhoufu.springbootresult.common;

/**
 * @ClassName : StatusCode
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:13
 * @Description : 定义一个状态码的接口，所有状态码都需要实现它
 */
public interface StatusCode {
    public int getCode();
    public String getMsg();
}
