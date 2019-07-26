package com.xyz.app.demo.controller;

import com.xyz.common.auth.annotation.SkipLogin;
import com.xyz.common.base.entity.AbstractController;
import com.xyz.common.client.feign.demo.RabbitMQClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController extends AbstractController {

    @Resource
    private RabbitMQClient rabbitMQClient;

    @GetMapping("/send")
    public String send() {
        String result = rabbitMQClient.send();
        return "the rabbit return " + result;
    }

    @GetMapping("/hello")
    @SkipLogin
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello2")
    public String hello2(){
        return "hello2";
    }
}
