package com.xyz.app.base;

import com.xyz.common.auth.exception.AuthException;
import com.xyz.common.base.DefaultExceptionControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
@RestControllerAdvice
public class AppDefaultExceptionControllerAdvice extends DefaultExceptionControllerAdvice {

    @ExceptionHandler(value = AuthException.class)
    public void handle(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.error("【统一异常处理url={}】",request.getRequestURI());
        log.error("错误信息",e);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.getWriter().write("\"你tm的没权限\"");
    }
}
