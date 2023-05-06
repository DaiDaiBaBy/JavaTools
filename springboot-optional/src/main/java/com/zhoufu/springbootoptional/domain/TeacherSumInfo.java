package com.zhoufu.springbootoptional.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : TeacherSumInfo
 * @Author : ZhouFu
 * @Date: 2023/5/6 10:58
 * @Description : 教师信息统计类
 */
@Data
public class TeacherSumInfo implements Serializable {
    private static final long serialVersionUID = 3594705637211687220L;

    private ProminentTeacherSumInfo prominentTeacherSumInfo;
}
