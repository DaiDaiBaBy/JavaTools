package com.zhoufu.springbootmockito.biz.order.service;

public interface IOrderService {
    /**
     * 新增订单
     * @param goodsId
     */
    void insertOrder(String goodsId);
}