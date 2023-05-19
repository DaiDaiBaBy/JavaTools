package com.zhoufu.springbootdynamictask.service;

import com.zhoufu.springbootdynamictask.common.Result;
import com.zhoufu.springbootdynamictask.entity.ScTaskInfo;
import com.zhoufu.springbootdynamictask.vo.TaskInfoReq;

import java.util.List;

/**
 * @ClassName : TaskInfoService
 * @Author : ZhouFu
 * @Date: 2023/5/18 15:39
 * @Description : 定时任务接口
 */
public interface TaskInfoService {

    /**获取任务列表分页*/

    Result selectTaskListByPage(TaskInfoReq taskInfoReq);

    /**添加定时任务*/

    Result addJob(TaskInfoReq taskInfoReq);

    /**更新任务*/

    Result updateJob(TaskInfoReq taskInfoReq);

    /**暂停任务*/

    Result pauseJob(Integer taskId);

    /**恢复任务*/

    Result resumeJob(Integer taskId);

    /**获取所有任务*/

    List<ScTaskInfo> selectTasks();

    /**删除任务*/

    Result delete(TaskInfoReq reqVo);

}