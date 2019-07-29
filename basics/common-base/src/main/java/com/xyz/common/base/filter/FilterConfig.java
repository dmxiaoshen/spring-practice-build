package com.xyz.common.base.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CommonFilter());
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        //此处添加需要排除的链接

        //List<String> excludePathPatternsList = Lists.newArrayList();

        //可以继续添加例外

        //登录入口例外
        //excludePathPatternsList.add("/login");
        //registrationBean.addInitParameter("str","/login");
        return registrationBean;
    }
}
