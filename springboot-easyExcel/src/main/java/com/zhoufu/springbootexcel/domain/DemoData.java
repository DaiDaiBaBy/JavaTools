package com.zhoufu.springbootexcel.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName : DemoData
 * @Author : ZhouFu
 * @Date: 2023/4/27 11:18
 * @Description : excel测试实体类
 * 设置Excel表头和添加的数据字段
 */
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty(value = "学生编号", index = 0)
    private int sno;

    //设置表头名称
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;
}
