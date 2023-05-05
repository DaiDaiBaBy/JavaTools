package com.zhoufu.springbootrabbitmqconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

/**
 * @ClassName : FanoutReceiverC
 * @Author : ZhouFu
 * @Date: 2023/5/5 18:16
 * @Description : 消费类
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiverC {
 
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("FanoutReceiverC消费者收到消息  : " +testMessage.toString());
    }
 
}