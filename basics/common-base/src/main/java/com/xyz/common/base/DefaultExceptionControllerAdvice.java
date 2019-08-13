package com.xyz.common.base;

import com.xyz.common.base.exception.LockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ComponentScan("com.xyz.common.base.util")
@RestControllerAdvice
@Slf4j
public class DefaultExceptionControllerAdvice {


    @ExceptionHandler(value = Exception.class)
    public void handle(Exception e, HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.error("【统一异常处理url={}】",request.getRequestURI());
        log.error("错误信息",e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.getWriter().write("\"服务器开小差了哈哈哈\"");
    }

    @ExceptionHandler(value = LockException.class)
    public void lock(LockException e,HttpServletRequest request, HttpServletResponse response) throws Exception{
        log.error("【统一异常处理url={}】",request.getRequestURI());
        log.error("错误信息",e);
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
        response.getWriter().write("\"处理中请勿频繁请求\"");
    }
}
