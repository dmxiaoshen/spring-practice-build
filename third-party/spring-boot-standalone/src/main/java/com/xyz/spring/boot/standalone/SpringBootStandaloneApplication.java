package com.xyz.spring.boot.standalone;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 不加exclude = SecurityAutoConfiguration.class
 * 则会报Caused by: java.lang.ArrayStoreException: sun.reflect.annotation.TypeNotPresentExceptionProxy
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootStandaloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStandaloneApplication.class, args);
    }

}
