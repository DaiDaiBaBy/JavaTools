package com.zhoufu.springbooteasypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
 * @ClassName : UserExportVO
 * @Author : ZhouFu
 * @Date: 2023/5/4 10:22
 * @Description : 对于Excel表格对应的单元格数据VO
 * 这里使用到了核心的一个注解@Excel，通过指定属性name来指定excel单元格的表头
 */
@Data
@AllArgsConstructor
public class UserExportVO implements Serializable {
    @Excel(name = "姓名", width = 15)
    private String realName;

    // replace：值的替换 导出是{a_id,b_id} 导入反过来。
    @Excel(name = "性别", replace = { "男_1", "女_2"} , suffix = "生")
    private Integer sex;

    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 15)
    private Date birthday;

    @Excel(name = "手机号码", width = 15)
    private String phone;

    @Excel(name = "邮箱", width = 20)
    private String email;

    // 图片处理
    // type =2 该字段类型为图片,imageType=1 (默认可以不填),表示从file读取,
    // 字段类型是个字符串类型可以用相对路径也可以用绝对路径,绝对路径优先依次获取
    @Excel(name = "头像地址", type = 2, imageType = 1, width = 40, height = 40)
    private String avatar;

    @Excel(name = "描述", width = 20)
    private String remark;
}
