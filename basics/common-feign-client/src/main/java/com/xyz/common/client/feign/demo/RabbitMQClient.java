package com.xyz.common.client.feign.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service-demo")
public interface RabbitMQClient {

    @GetMapping("/rabbitmq/send")
    public String send();
}
