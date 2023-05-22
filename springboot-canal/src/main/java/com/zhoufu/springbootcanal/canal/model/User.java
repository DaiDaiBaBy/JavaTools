package com.zhoufu.springbootcanal.canal.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName : User
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:17
 * @Description : 测试实体类
 */
@Data
@Table(name = "t_user")
public class User implements Serializable {
    private static final long serialVersionUID = -7128002825151472196L;

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 性别
     */
    @Column(name = "sex")
    private Integer sex;

    /**
     * 备注
     */
    private String remark;

    /**
     * 时间
     */
    private Date date;

}
