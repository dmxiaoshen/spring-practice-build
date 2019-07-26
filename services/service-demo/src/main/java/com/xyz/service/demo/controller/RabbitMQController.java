package com.xyz.service.demo.controller;

import com.xyz.common.base.entity.AbstractController;
import com.xyz.common.base.rabbitmq.CustomMQMessage;
import com.xyz.common.base.rabbitmq.DefaultRabbitMQSender;
import com.xyz.common.base.rabbitmq.MQConstantContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rabbitmq")
@Slf4j
public class RabbitMQController extends AbstractController {

    @Resource
    private DefaultRabbitMQSender defaultRabbitMQSender;

    @Value("${server.port}")
    private String port;

    @GetMapping("/send")
    public String send() {
        for(int i=0;i<10;i++) {
            defaultRabbitMQSender.send(MQConstantContainer.TOPIC_EVENT_TEST, new CustomMQMessage("hello-test"+i));
            defaultRabbitMQSender.send(MQConstantContainer.TOPIC_EVENT_TEST2, new CustomMQMessage("hello-test2"+i));

        }
        return "success "+port;
    }
}
