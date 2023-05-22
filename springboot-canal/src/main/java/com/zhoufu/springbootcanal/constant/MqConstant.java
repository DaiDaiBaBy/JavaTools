package com.zhoufu.springbootcanal.constant;

/**
 * @ClassName : MqConstant
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:07
 * @Description : 全局常用变量--mq
 */
public interface MqConstant {

    /**
     * 简单模式
     */
    String SIMPLE_QUEUE = "simple_queue";
    /**
     * 路由模式
     */
    String CANAL_EXCHANGE = "canal.exchange";
    String CANAL_QUEUE = "canal_queue";
    String CANAL_ROUTING_KEY = "canal_routing_key";
}
