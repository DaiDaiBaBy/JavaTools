package com.zhoufu.springbootcanal.canal.mq;

import com.alibaba.fastjson.JSON;
import com.zhoufu.springbootcanal.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : CanalRabbitMqListener
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:18
 * @Description : canalRabbitMq监听数据库变化
 */
@Slf4j
@Component
public class CanalRabbitMqListener {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = MqConstant.CANAL_QUEUE, durable = "true"),
                    exchange = @Exchange(value = MqConstant.CANAL_EXCHANGE),
                    key = MqConstant.CANAL_ROUTING_KEY
            )
    })
    public void handleCanalDataChange(String message) {
        log.info("[canal] 接收消息: {}", JSON.toJSONString(message));
    }

}
