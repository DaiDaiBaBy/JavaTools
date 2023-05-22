package com.zhoufu.springbootcanal.api;

import com.zhoufu.springbootcanal.rabbitmq.SimpleMsgProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : RabbitController
 * @Author : ZhouFu
 * @Date: 2023/5/22 14:26
 * @Description : 测试RabbitMQ
 */
@Slf4j
@Api(tags = "测试mq")
@RestController
@RequestMapping
public class RabbitController {

    private final SimpleMsgProducer simpleMsgProducer;

    @Autowired
    public RabbitController(SimpleMsgProducer simpleMsgProducer) {
        this.simpleMsgProducer = simpleMsgProducer;
    }

    @ApiOperation("simpleQueue")
    @PostMapping("simpleQueue")
    public String simpleQueue() {
        this.simpleMsgProducer.send();
        return "SUCCESS";
    }

}
