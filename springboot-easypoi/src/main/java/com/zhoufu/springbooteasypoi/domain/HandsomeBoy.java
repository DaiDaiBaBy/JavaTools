package com.zhoufu.springbooteasypoi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName : HandsomeBoy
 * @Author : ZhouFu
 * @Date: 2023/5/4 14:21
 * @Description : 具体信息 模型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandsomeBoy {

    /** 姓名 */
    private String name;

    /** 性别 */
    private String gender;

    /** 年龄 */
    private int age;

    /** 爱好 */
    private String hobby;

    /** 帅气值 */
    private String handsomeValue;

    /** 座右铭 */
    private String motto;
}
