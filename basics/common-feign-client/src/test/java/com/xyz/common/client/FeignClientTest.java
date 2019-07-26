package com.xyz.common.client;

import com.xyz.common.client.feign.demo.RabbitMQClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitMQClient.class)
@Import({FeignAutoConfiguration.class, HttpMessageConvertersAutoConfiguration.class})
@EnableFeignClients(clients = FeignClientTest.TestRabbitMQClient.class)
public class FeignClientTest {

    @FeignClient(value = "service-demo", url = "http://localhost:8092")
    public interface TestRabbitMQClient extends RabbitMQClient {
    }

    @Resource
    private TestRabbitMQClient testRabbitMQClient;

    @Test
    public void test() {
        System.out.println(testRabbitMQClient.send());
    }

}
