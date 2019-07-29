package com.xyz.common.auth;

import com.xyz.common.auth.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableConfigurationProperties(ExcludeUrlProperties.class)
@ConditionalOnProperty(value = "app.login.enable", havingValue = "true")
public class CommonAuthAutoConfig implements WebMvcConfigurer {

    @Autowired
    private ExcludeUrlProperties excludeUrlProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new AuthInterceptor());
        if (excludeUrlProperties.getExcludeUrls()!=null) {
            registration.excludePathPatterns(excludeUrlProperties.getExcludeUrls());
        }
//        List<String> excludeUrls = new ArrayList<>();
//        excludeUrls.add("/webjars/**");
//        excludeUrls.add("/doc*");
//        excludeUrls.add("/swagger*");
//        excludeUrls.add("/v2/api-docs*");
//        excludeUrls.add("/fav*");
//        excludeUrls.add("/error*");
//        registration.excludePathPatterns(excludeUrls);
    }
}
