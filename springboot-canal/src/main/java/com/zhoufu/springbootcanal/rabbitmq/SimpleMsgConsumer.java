package com.zhoufu.springbootcanal.rabbitmq;

import com.zhoufu.springbootcanal.constant.MqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName : SimpleMsgConsumer
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:08
 * @Description : mq消费者
 */
@Component
@Slf4j
public class SimpleMsgConsumer {

    @RabbitListener(queues = MqConstant.SIMPLE_QUEUE)
    public void listener(String msg) {
        log.info("[消费者] 接收消息: {}", msg);
    }
}
