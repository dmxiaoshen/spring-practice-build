package com.xyz.common.base.rabbitmq;

public abstract class AbstractConsumer {

    public abstract void handle(CustomMQMessage customMQMessage);
}
