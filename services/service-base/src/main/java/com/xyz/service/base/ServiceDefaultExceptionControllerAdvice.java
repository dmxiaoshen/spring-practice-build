package com.xyz.service.base;

import com.xyz.common.base.DefaultExceptionControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Configuration
@RestControllerAdvice
public class ServiceDefaultExceptionControllerAdvice extends DefaultExceptionControllerAdvice {


}
