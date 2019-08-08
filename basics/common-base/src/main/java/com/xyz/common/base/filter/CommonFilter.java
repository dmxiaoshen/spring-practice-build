package com.xyz.common.base.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.info("==CommonFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String url = null;
        if (request instanceof HttpServletRequest) {
             url = ((HttpServletRequest)request).getRequestURL().toString();
        }
        log.info("==CommonFilter start url={}",url);
        long start = new Date().getTime();
        chain.doFilter(request, response);
        log.info("==CommonFilter 耗时:" + (new Date().getTime() - start));
        log.info("==CommonFilter finish url={}",url);
    }

    @Override
    public void destroy() {
        log.info("==CommonFilter destory");
    }
}
