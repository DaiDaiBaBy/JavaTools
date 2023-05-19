package com.zhoufu.springbootdynamictask.dao;

import com.zhoufu.springbootdynamictask.entity.ScTaskInfo;
import com.zhoufu.springbootdynamictask.entity.ScTaskInfoExample;
import java.util.List;

import com.zhoufu.springbootdynamictask.vo.TaskInfoReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ScTaskInfoMapper {

    ScTaskInfo selectByJobName(String jobName);

    List<ScTaskInfo> selectAll();

    List<ScTaskInfo> selectTaskInfos(TaskInfoReq taskInfo);

    long countByExample(ScTaskInfoExample example);

    int deleteByExample(ScTaskInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ScTaskInfo record);

    int insertSelective(ScTaskInfo record);

    List<ScTaskInfo> selectByExample(ScTaskInfoExample example);

    ScTaskInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ScTaskInfo record, @Param("example") ScTaskInfoExample example);

    int updateByExample(@Param("record") ScTaskInfo record, @Param("example") ScTaskInfoExample example);

    int updateByPrimaryKeySelective(ScTaskInfo record);

    int updateByPrimaryKey(ScTaskInfo record);
}