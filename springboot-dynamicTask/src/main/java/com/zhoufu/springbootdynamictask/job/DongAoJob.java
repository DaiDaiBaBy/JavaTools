package com.zhoufu.springbootdynamictask.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @ClassName : DongAoJob
 * @Author : ZhouFu
 * @Date: 2023/5/18 16:03
 * @Description : 定义要执行的业务逻辑任务 DongAoJob 类
 * 定义一个调度器要执行的任务
 */
@Component
public class DongAoJob extends QuartzJobBean {

    private static final Log logger = LogFactory.getLog(DongAoJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("幼年是盼盼，青年是晶晶，中年是冰墩墩，生活见好逐渐发福");

    }

}