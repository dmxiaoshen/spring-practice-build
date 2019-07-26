package com.xyz.common.auth;

import com.xyz.common.auth.interceptor.AuthInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnProperty(value = "app.login.enable", havingValue = "true")
public class CommonAuthAutoConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new AuthInterceptor());
        List<String> excludeUrls = new ArrayList<>();
//        excludeUrls.add("/webjars/**");
//        excludeUrls.add("/doc*");
//        excludeUrls.add("/swagger*");
//        excludeUrls.add("/v2/api-docs*");
//        excludeUrls.add("/fav*");
//        excludeUrls.add("/error*");
        registration.excludePathPatterns(excludeUrls);
    }
}
