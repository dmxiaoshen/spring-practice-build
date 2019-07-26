package com.xyz.common.base.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
public abstract class AbstractProducer {

    protected void send(RabbitTemplate rabbitTemplate, String topic, CustomMQMessage customMQMessage){

        try {
            rabbitTemplate.setChannelTransacted(false);
            if (!rabbitTemplate.isConfirmListener()) {
                rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                    @SuppressWarnings("static-access")
                    @Override
                    public void confirm(CorrelationData correlationData, boolean ack, String s) {
                        if (!ack) {
                            customMQMessage.addSendNum();
                            if (customMQMessage.isAgainSend()) {
                                rabbitTemplate.convertAndSend(topic, topic, customMQMessage);
                            } else {
                                log.error("队列【"+topic+"】MQ消息连续发送" + customMQMessage.MAX_SENDNUM + "次失败");
                            }
                        }
                    }
                });
            }
            rabbitTemplate.convertAndSend(topic, topic, customMQMessage);
        } catch (Exception e) {
            log.error("队列【"+topic+"】MQ消息发送失败：" + e);
        }

    }

    public abstract void send(String topic,CustomMQMessage customMQMessage);
}
