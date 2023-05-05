package com.zhoufu.springbootrabbitmqconsumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName : DirectReceiver
 * @Author : ZhouFu
 * @Date: 2023/5/5 17:44
 * @Description : 创建消息接收监听类
 */
@Component
@RabbitListener(queues = "TestDirectQueue") //监听的队列名称 TestDirectQueue
public class DirectReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        // 然后将rabbitmq-consumer项目运行起来，可以看到把之前推送的那条消息消费下来了
        // 可以再继续调用rabbitmq-provider项目的推送消息接口，可以看到消费者即时消费消息
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }
}
