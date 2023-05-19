package com.zhoufu.springbootdynamictask.vo;

import lombok.Data;
/**
 * @ClassName : TaskInfoReq
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:48
 * @Description : 工具类&辅助代码
 * 任务请求类
 */
@Data
public class TaskInfoReq {
    /**
     * 任务编号
     */

    private Integer id;

    /**
     * 任务时间表达式
     */

    private String cron;

    /**
     * 任务状态
     */

    private String status;

    /**
     * 任务名称
     */

    private String jobName;

    /**
     * 每页显示条数
     */

    private int pageSize = 10;

    /**
     * 当前页数
     */

    private int pageCurrent = 1;

}