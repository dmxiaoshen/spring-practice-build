package com.xyz.service.demo.query;

import com.xyz.common.base.rabbitmq.CustomMQMessage;
import com.xyz.common.base.rabbitmq.DefaultRabbitMQSender;
import com.xyz.common.base.rabbitmq.MQConstantContainer;
import com.xyz.common.base.util.SpringContextUtil;

public class TestTask implements Runnable{

    private DefaultRabbitMQSender defaultRabbitMQSender = SpringContextUtil.getBean(DefaultRabbitMQSender.class);
    @Override
    public void run() {
        while(true) {
            defaultRabbitMQSender.send(MQConstantContainer.TOPIC_EVENT_TEST, new CustomMQMessage("hhh"));
            defaultRabbitMQSender.send(MQConstantContainer.TOPIC_EVENT_TEST2, new CustomMQMessage("hhh-1"));
        }
    }
}
