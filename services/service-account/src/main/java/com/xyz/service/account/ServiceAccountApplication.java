package com.xyz.service.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.xyz.service.account.dao")
public class ServiceAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAccountApplication.class, args);
    }

}
