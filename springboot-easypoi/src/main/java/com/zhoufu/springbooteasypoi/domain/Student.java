package com.zhoufu.springbooteasypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @ClassName : Student
 * @Author : ZhouFu
 * @Date: 2023/5/4 14:07
 * @Description : Student数据模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student extends School {

    /**
     * name指定导出excel时生成的列名 orderNum可指定导出的该属性对应的所在列的位置 width设置单元格宽度 type设置导出类型 1是文本,
     * 2是图片, 3是函数,10 数字 默认是文本
     */
    @Excel(name = "学号", orderNum = "1", type = 10, width = 15)
    private String studentID;

    @Excel(name = "姓名", orderNum = "2", width = 15)
    private String name;

    /**
     * 当gender为1时，导出的结果为 男， 当gender为0时，导出的结果为 女 mergeVertical设置是否纵向合并列
     */
    @Excel(name = "性别", mergeVertical = true, replace = { "男_1", "女_0" }, orderNum = "3", width = 5)
    private Integer gender;

    /**
     * type设置导出类型 1是文本, 2是图片, 3是函数, 10是数字  默认是文本
     */
    @Excel(name = "年龄", orderNum = "4", type = 10, width = 5)
    private int age;

    /**
     * 将Data日期导出为yyyy-MM-dd格式 mergeVertical设置是否纵向合并列
     * mergeRely设置合并列的前提条件，即:只有当索引为2的列(即:"性别"列)已经 合并了时，那么此时这一列的纵向相同时，才能纵向合并;如果引为2的
     * 列(即:"性别"列)纵向数据不同，那么就算此列的纵向数据相同，那么 也不会合并
     */
    @Excel(name = "入校时间", mergeVertical = true, mergeRely = { 2 }, format = "yyyy-MM-dd", orderNum = "5", width = 20)
    private Date entranceTime;

    @Excel(name = "班级", orderNum = "7", width = 15)
    private String className;
}
