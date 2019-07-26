package com.xyz.common.base.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.*;

import java.io.IOException;

@Configuration
@Slf4j
@ComponentScan("com.xyz.common.base.rabbitmq")
public class RabbitmqConfig {

    @Bean("myMQConnectionFactory")
    @Primary
    public ConnectionFactory myMQConnectionFactory(@Value("${spring.rabbitmq.host}") String host,
                                                   @Value("${spring.rabbitmq.port}") Integer port,
                                                   @Value("${spring.rabbitmq.username}") String username,
                                                   @Value("${spring.rabbitmq.password}") String password) {
        return getConnectionFactory(host, port, username, password);
    }

    @Bean(name = "myRabbitTemplate")
    @Scope("prototype")
    public RabbitTemplate myRabbitTemplate(
            @Qualifier("myMQConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate myRabbitTemplate = new RabbitTemplate(connectionFactory);
        return myRabbitTemplate;
    }

    private CachingConnectionFactory getConnectionFactory(String host, Integer port, String username, String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(false);
        return connectionFactory;
    }

    @Bean(name = "myMQListenerFactory")
    public SimpleRabbitListenerContainerFactory myMQListenerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                    @Qualifier("myMQConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public String queue1(@Qualifier("myMQConnectionFactory") ConnectionFactory connectionFactory) throws IOException {
        try {
            buildQueue(MQConstantContainer.TOPIC_EVENT_TEST, connectionFactory);
            return "success";
        } catch (Exception e) {
            throw new RuntimeException("【error】 queue " + MQConstantContainer.TOPIC_EVENT_TEST + " build failure");
        }
    }

    @Bean(name = "myThreadMQListenerFactory")
    public SimpleRabbitListenerContainerFactory myThreadMQListenerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                                          @Qualifier("myMQConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConcurrentConsumers(20);//线程数
        factory.setMaxConcurrentConsumers(20);//最大线程数
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public String queue2(@Qualifier("myMQConnectionFactory") ConnectionFactory connectionFactory) throws IOException {
        try {
            buildQueue(MQConstantContainer.TOPIC_EVENT_TEST2, connectionFactory);
            return "success";
        } catch (Exception e) {
            throw new RuntimeException("【error】 queue " + MQConstantContainer.TOPIC_EVENT_TEST2 + " build failure");
        }
    }


    private void buildQueue(String topic, ConnectionFactory connectionFactory) throws IOException {
        // 参数为 (boolean transactional)
        Channel channel = connectionFactory.createConnection().createChannel(false);
        channel.exchangeDeclare(topic, "direct", true);
        // 第二个参数 ture 为durable 永不丢失
        channel.queueDeclare(topic, true, false, false, null);
        //queue,exchange,routeKey
        channel.queueBind(topic, topic, topic);
    }
}
