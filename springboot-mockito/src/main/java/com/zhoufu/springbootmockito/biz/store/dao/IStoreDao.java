package com.zhoufu.springbootmockito.biz.store.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * 仓库持久层
 */
@Repository
public interface IStoreDao {
    /**
     * 统计剩余商品数量
     * @param goodsId
     * @return
     */
    @Select("SELECT CURRENT_COUNT FROM T_STORE WHERE GOODS_ID = #{goodsId}")
    Integer countLeftGoods(String goodsId);

    /**
     * 减库存
     * @param goodsId
     */
    @Update("UPDATE T_STORE SET CURRENT_COUNT = CURRENT_COUNT-1 WHERE GOODS_ID = #{goodsId}")
    void reduceGoods(String goodsId);
}