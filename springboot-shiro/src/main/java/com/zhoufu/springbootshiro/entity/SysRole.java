package com.zhoufu.springbootshiro.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *  实体类SysTole  权限
 */
@Entity
public class SysRole implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2662493471572546262L;

    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    private String description;

    private Boolean available = Boolean.FALSE;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = { @JoinColumn(name = "permissionId") })
    private List<SysPermission> permissions;

    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = { @JoinColumn(name = "uid") })
    private List<UserInfo> userInfos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }

}
