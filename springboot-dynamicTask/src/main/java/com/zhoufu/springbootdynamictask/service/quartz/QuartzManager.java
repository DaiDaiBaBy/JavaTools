package com.zhoufu.springbootdynamictask.service.quartz;

import com.zhoufu.springbootdynamictask.common.EnumTaskEnable;
import com.zhoufu.springbootdynamictask.entity.ScTaskInfo;
import com.zhoufu.springbootdynamictask.service.TaskInfoService;
import com.zhoufu.springbootdynamictask.vo.TaskInfoReq;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName : QuartzManager
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:33
 * @Description : 任务启动管理
 * Spring Boot 容器启动时，加载启动所有任务
 */
@Component
public class QuartzManager {
    private Logger logger = LoggerFactory.getLogger(QuartzManager.class);

    private final Scheduler scheduler;
    private final SpringJobFactory springJobFactory;
    private final TaskInfoService taskInfoService;

    @Autowired
    public QuartzManager(Scheduler scheduler, SpringJobFactory springJobFactory, TaskInfoService taskInfoService) {
        this.scheduler = scheduler;
        this.springJobFactory = springJobFactory;
        this.taskInfoService = taskInfoService;
    }

    @PostConstruct
    public void start() {
        //启动所有任务
        try {
            scheduler.setJobFactory(springJobFactory);
            // scheduler.clear();
            List<ScTaskInfo> tasks = taskInfoService.selectTasks();
            for (ScTaskInfo taskInfo : tasks) {
                if (EnumTaskEnable.START.getCode().equals(taskInfo.getStatus()) && !StringUtils.isEmpty(taskInfo.getCron())) {
                    TaskInfoReq data=new TaskInfoReq();
                    BeanUtils.copyProperties(taskInfo,data);
                    taskInfoService.addJob(data);
                }
            }
            logger.info("定时任务启动完成");
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("定时任务初始化失败");
        }
    }

}
