package com.zhoufu.springbooteasypoi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName : InterviewResult
 * @Author : ZhouFu
 * @Date: 2023/5/4 14:22
 * @Description : 采访结果 模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewResult {

    /** 标题 */
    private String title;

    /** 日期 */
    private String date;

    /** 采访人 */
    private String interviewer;

    /** 信息集合 */
    private List<HandsomeBoy> list;
}
