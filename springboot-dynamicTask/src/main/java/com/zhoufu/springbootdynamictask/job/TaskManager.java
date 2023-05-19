package com.zhoufu.springbootdynamictask.job;

import com.zhoufu.springbootdynamictask.entity.ScTaskInfo;
import com.zhoufu.springbootdynamictask.utils.SpringContextUtils;
import com.zhoufu.springbootdynamictask.vo.TaskInfoReq;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : TaskManager
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:31
 * @Description : 任务管理
 * 1、添加任务   2、更新任务  3、暂停任务   4、恢复任务
 */
@Component
public class TaskManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskManager.class);
    public static final String JOB_DEFAULT_GROUP_NAME = "JOB_DEFAULT_GROUP_NAME";
    public static final String TRIGGER_DEFAULT_GROUP_NAME = "TRIGGER_DEFAULT_GROUP_NAME";

    private final Scheduler scheduler;

    private final SpringContextUtils springContextUtils;

    @Autowired
    public TaskManager(Scheduler scheduler, SpringContextUtils springContextUtils) {
        this.scheduler = scheduler;
        this.springContextUtils = springContextUtils;
    }

    /**
     * 添加任务
     */
    public boolean addJob(TaskInfoReq taskInfoReq) {
        boolean flag = true;
        if (!CronExpression.isValidExpression(taskInfoReq.getCron())) {
            LOGGER.error("定时任务表达式有误：{}", taskInfoReq.getCron());
            return false;
        }

        try {
            String className = springContextUtils.getBean(taskInfoReq.getJobName()).getClass().getName();
            JobDetail jobDetail = JobBuilder.newJob().withIdentity(new JobKey(taskInfoReq.getJobName(), JOB_DEFAULT_GROUP_NAME))
                    .ofType((Class<Job>) Class.forName(className))
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .forJob(jobDetail)
                    .withSchedule(CronScheduleBuilder.cronSchedule(taskInfoReq.getCron()))
                    .withIdentity(new TriggerKey(taskInfoReq.getJobName(), TRIGGER_DEFAULT_GROUP_NAME))
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception e) {
            LOGGER.error("添加定时任务异常：{}", e.getMessage(), e);
            flag = false;
        }
        return flag;
    }


    /**
     * 更新任务
     */
    public boolean updateJob(ScTaskInfo taskInfo) {
        boolean flag = true;
        try {
            JobKey jobKey = new JobKey(taskInfo.getJobName(), JOB_DEFAULT_GROUP_NAME);
            TriggerKey triggerKey = new TriggerKey(taskInfo.getJobName(), TRIGGER_DEFAULT_GROUP_NAME);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            if (scheduler.checkExists(jobKey) && scheduler.checkExists(triggerKey)) {
                Trigger newTrigger = TriggerBuilder.newTrigger()

                        .forJob(jobDetail)

                        .withSchedule(CronScheduleBuilder.cronSchedule(taskInfo.getCron()))

                        .withIdentity(triggerKey)

                        .build();

                scheduler.rescheduleJob(triggerKey, newTrigger);
            } else {
                LOGGER.info("更新任务失败，任务不存在，任务名称：{}，表达式：{}", taskInfo.getJobName(), taskInfo.getCron());
            }
            LOGGER.info("更新任务成功，任务名称：{}，表达式：{}", taskInfo.getJobName(), taskInfo.getCron());
        } catch (SchedulerException e) {
            LOGGER.error("更新定时任务失败:{}", e.getMessage(), e);
            flag = false;
        }
        return flag;

    }

    /**
     * 暂停任务
     */
    public boolean pauseJob(ScTaskInfo taskInfo) {
        try {
            scheduler.pauseJob(JobKey.jobKey(taskInfo.getJobName(), JOB_DEFAULT_GROUP_NAME));
            LOGGER.info("任务暂停成功：{}", taskInfo.getId());
            return true;
        } catch (
                SchedulerException e) {
            LOGGER.error("暂停定时任务失败:{}", e.getMessage(), e);
            return false;
        }

    }


    /**
     * 恢复任务
     */
    public boolean resumeJob(ScTaskInfo taskInfo) {

        try {
            scheduler.resumeJob(JobKey.jobKey(taskInfo.getJobName(), JOB_DEFAULT_GROUP_NAME));
            LOGGER.info("任务恢复成功：{}", taskInfo.getId());
            return true;
        } catch (SchedulerException e) {
            LOGGER.error("恢复定时任务失败:{}", e.getMessage(), e);
            return false;

        }

    }
}
