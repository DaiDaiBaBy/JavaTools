package com.zhoufu.springbootoptional.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName : ProminentTeacherSumInfo
 * @Author : ZhouFu
 * @Date: 2023/5/6 11:00
 * @Description : 优秀教师信息统计类
 */
@Data
public class ProminentTeacherSumInfo implements Serializable {
    private static final long serialVersionUID = -2532646366328759758L;

    private String mostManName;
    private Integer count;
    private List<String> names;
}
