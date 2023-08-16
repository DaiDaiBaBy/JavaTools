package com.zhoufu.springbootmockito.biz.store.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * 仓库服务
 */
@Transactional
public interface IStoreService {
    /**
     * 统计剩余商品数量
     * @param goodsId
     * @return
     */
    Integer countLeftGoods(String goodsId);

    /**
     * 减库存
     * @param goodsId
     */
    void reduceGoods(String goodsId);
}