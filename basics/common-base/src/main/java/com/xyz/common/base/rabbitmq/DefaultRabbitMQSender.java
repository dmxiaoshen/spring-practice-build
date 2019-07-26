package com.xyz.common.base.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("defaultRabbitMQSender")
@Slf4j
@Scope("prototype")
public class DefaultRabbitMQSender extends AbstractProducer {

    @Resource(name = "myRabbitTemplate")
    private RabbitTemplate myRabbitTemplate;

    @Override
    public void send(String topic, CustomMQMessage customMQMessage) {
        send(myRabbitTemplate, topic, customMQMessage);
    }
}
