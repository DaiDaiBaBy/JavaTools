package com.zhoufu.springbootoptional.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @ClassName : SchoolSumInfo
 * @Author : ZhouFu
 * @Date: 2023/5/6 10:57
 * @Description : 学校信息统计类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolSumInfo implements Serializable {
    private static final long serialVersionUID = 8665306202252208786L;

    private String schoolName;
    private TeacherSumInfo teacherSumInfo;
    private StudentSumInfo studentSumInfo;
    public SchoolSumInfo(String schoolName) {
        this.schoolName = schoolName;
    }
}
