package com.zhoufu.springbootdynamictask.common;

import lombok.Data;

/**
 * @ClassName : Result
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:48
 * @Description : Result类
 */
@Data
public class Result {

    private int code;

    private String msg;

    private Object retData;

}
