package com.zhoufu.springbootdatabaselock.model;


import lombok.Data;

import javax.persistence.*;

/**
 *  存储商品信息
 */
@Data
@Entity
@Table(name = "commodity")
public class Commodity {
    @Id
    private Integer id;
    //商品数量
    private Integer number;
    //商品名称
    private String commodityName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
