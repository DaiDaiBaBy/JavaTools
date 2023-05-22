package com.zhoufu.springbootcanal.rabbitmq;

import com.zhoufu.springbootcanal.constant.MqConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName : SimpleRabbitMqConfig
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:10
 * @Description : mq配置
 */
@Configuration
public class SimpleRabbitMqConfig {
    @Bean
    public Queue simpleQueue() {
        // durable: true 标识开启消息队列持久化 (队列当中的消息在重启rabbitmq服务的时候还会存在)
        return new Queue(MqConstant.SIMPLE_QUEUE, true);
    }

}
