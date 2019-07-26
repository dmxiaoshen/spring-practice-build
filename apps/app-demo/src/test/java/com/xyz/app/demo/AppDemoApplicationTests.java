package com.xyz.app.demo;

import com.xyz.common.client.feign.demo.RabbitMQClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppDemoApplication.class)
public class AppDemoApplicationTests {
    @Resource
    private RabbitMQClient rabbitMQClient;

    @Test
    public void contextLoads() {
        System.out.println(rabbitMQClient.send());
    }

}
