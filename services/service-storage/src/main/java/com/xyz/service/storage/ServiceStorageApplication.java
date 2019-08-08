package com.xyz.service.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.xyz.service.storage.dao")
public class ServiceStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStorageApplication.class, args);
    }

}
