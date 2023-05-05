package com.zhoufu.springbootresult.domain;

import com.zhoufu.springbootresult.common.ResultCode;
import com.zhoufu.springbootresult.common.StatusCode;
import lombok.Data;
/**
 * @ClassName : ResultVo
 * @Author : ZhouFu
 * @Date: 2023/5/5 13:17
 * @Description : ResultVo 包装类
 */
@Data
public class ResultVo {
    // 状态码
    private int code;
    // 状态信息
    private String msg;
    // 返回对象
    private Object data;
    // 手动设置返回vo
    public ResultVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    // 默认返回成功状态码，数据对象
    public ResultVo(Object data) {
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }
    // 返回指定状态码，数据对象
    public ResultVo(StatusCode statusCode, Object data) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }
    // 只返回状态码
    public ResultVo(StatusCode statusCode) {
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = null;
    }
}