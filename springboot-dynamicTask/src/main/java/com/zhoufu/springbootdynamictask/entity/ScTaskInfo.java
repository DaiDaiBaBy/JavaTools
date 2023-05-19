package com.zhoufu.springbootdynamictask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScTaskInfo implements Serializable {
    private static final long serialVersionUID = -8771752340955155361L;
    private Integer id;

    private String cron;

    private String jobName;

    private String status;

    private Date createTime;

    private Date updateTime;
}