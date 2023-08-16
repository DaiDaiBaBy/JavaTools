package com.zhoufu.springbootmockito.biz.order.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {
    /**
     * 新增订单
     * @param goodsId
     */
    @Insert("INSERT INTO T_ORDER(GOODS_ID) VALUES(#{goodsId})")
    void insertOrder(String goodsId);
}