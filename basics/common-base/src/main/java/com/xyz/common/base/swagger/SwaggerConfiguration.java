package com.xyz.common.base.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 如果这里extends WebMvcConfigurationSupport的话
 * springboot 2.0 配置 spring.jackson.date-format 不生效
 * 因此 implements WebMvcConfigurer
 */
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(value = "app.swagger.enable", havingValue = "true")
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                //.termsOfServiceUrl("http://localhost:8080/")
                .contact(new Contact(swaggerProperties.getAuthor(), "url", "email"))
                .version(swaggerProperties.getVersion())
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "DELETE", "PUT")
                .allowedHeaders("Authorization", "Accept", "Origin", "X-Requested-With", "Content-Type", "Last-Modified")
                .maxAge(30 * 60)
                .allowCredentials(true);//设置成允许操作cookie
    }

//    /**
//     * 这里配置了swagger，因此自定义拦截器的注册放到同一个webConfig中
//     * @param registry
//     */
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
//        List<String> excludeUrls = Lists.newArrayList();
//        excludeUrls.add("/webjars/**");
//        excludeUrls.add("/doc*");
//        excludeUrls.add("/swagger*");
//        excludeUrls.add("/v2/api-docs*");
//        excludeUrls.add("/user/*");
//        excludeUrls.add("/fav*");
//        registration.excludePathPatterns(excludeUrls);
//    }

//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(new AuthInterceptor());
//        List<String> excludeUrls = Lists.newArrayList();
//        excludeUrls.add("/webjars/**");
//        excludeUrls.add("/doc*");
//        excludeUrls.add("/swagger*");
//        excludeUrls.add("/v2/api-docs*");
//        excludeUrls.add("/fav*");
//        excludeUrls.add("/error*");
//        registration.excludePathPatterns(excludeUrls);
//    }
}
