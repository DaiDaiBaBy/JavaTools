package com.zhoufu.springbooteasypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @ClassName : School
 * @Author : ZhouFu
 * @Date: 2023/5/4 14:08
 * @Description : 学校模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {

    /** 学校名称 */
    @Excel(name = "学校名称", orderNum = "6", width = 20)
    private String schoolName;

    /** 学校地址 */
    @Excel(name = "学校地址", orderNum = "8", width = 20)
    private String schoolAddress;
}
