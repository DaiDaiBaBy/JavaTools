package com.zhoufu.springbootshiro.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *  实体类 UserInfo
 */
@Entity
public class UserInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2408336391165537166L;

    @Id
    @GeneratedValue
    private Integer uid;

    @Column(unique = true)
    private String username;

    private String name;

    private String password;

    private String salt;

    private byte state;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = { @JoinColumn(name = "roleId") })
    private List<SysRole> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * 密码盐.
     *
     * @return
     */
    public String getCredentialsSalt() {
        //重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
        //return this.username + this.salt;
        //为了测试方便，我们简单定义盐为frank
        String salt = "frank";
        return salt;
    }
}
