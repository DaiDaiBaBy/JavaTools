package com.zhoufu.springbootdistributedxxxjob.scheduled;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobContext;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description 基于 xxlJob实现分布式任务调度
 * @Author zf
 **/

@Configuration
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "xxlJob")
@Component
public class XxlJobScheduled {
    private static final Logger log = LoggerFactory.getLogger(XxlJobScheduled.class);

    @XxlJob("xxlJobTask")
    public ReturnT<String> xxlJobTask(String data) {
        XxlJobContext xxlJobContext = XxlJobContext.getXxlJobContext();
        String jobParam = xxlJobContext.getJobParam();
        try {
            //模拟业务处理线程休眠10秒
            Thread.sleep(10000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("xxlJobTest定时任务执行成功,jobParam:{},data={}",jobParam,data);
        return ReturnT.SUCCESS;
    }
}
