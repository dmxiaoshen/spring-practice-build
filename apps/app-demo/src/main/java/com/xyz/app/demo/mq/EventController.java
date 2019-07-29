package com.xyz.app.demo.mq;

import com.xyz.common.base.rabbitmq.AbstractConsumer;
import com.xyz.common.base.rabbitmq.CustomMQMessage;
import com.xyz.common.base.rabbitmq.MQConstantContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@RabbitListener(queues = MQConstantContainer.TOPIC_EVENT_TEST,containerFactory = "myThreadMQListenerFactory")
public class EventController extends AbstractConsumer {


    @RabbitHandler
    @Override
    public void handle(CustomMQMessage customMQMessage) {
        String msg = (String)customMQMessage.getBody();
        log.info("消费-{}",msg);
//        try {
//            Thread.sleep(20000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
