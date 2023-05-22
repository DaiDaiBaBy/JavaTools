package com.zhoufu.springbootcanal.rabbitmq;

import com.zhoufu.springbootcanal.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SimpleMsgProducer
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:09
 * @Description : mq生产者
 */
@Slf4j
@Component
public class SimpleMsgProducer {

    private final AmqpTemplate rabbitTemplate;

    @Autowired
    public SimpleMsgProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send() {
        String msgContent = "Hello World";
        log.info("[生产者] 发送消息: {}", msgContent);
        this.rabbitTemplate.convertAndSend(MqConstant.SIMPLE_QUEUE, msgContent);
    }
}
