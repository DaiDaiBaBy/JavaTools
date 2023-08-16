package com.zhoufu.springbootmockito.biz.order.service.impl;

import com.zhoufu.springbootmockito.biz.order.dao.OrderDao;
import com.zhoufu.springbootmockito.biz.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    /**
     * 新增订单
     * @param goodsId
     */
    @Override
    public void insertOrder(String goodsId) {
        orderDao.insertOrder(goodsId);
    }
}