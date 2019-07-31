package com.xyz.common.auth.interceptor;


import com.xyz.common.utils.JwtUtils;
import com.xyz.common.auth.annotation.SkipLogin;
import com.xyz.common.auth.common.CommonConstants;
import com.xyz.common.auth.common.TokenHolder;
import com.xyz.common.auth.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===AuthInterceptor preHandle");
        log.info(request.getRequestURI());

        //如果不是映射到Controller层所谓的方法则直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果有SkipLogin注解则表示跳过登录
        if (method.isAnnotationPresent(SkipLogin.class)) {
            if (method.getAnnotation(SkipLogin.class).required()) {
                return true;
            }
        } else {
            String token = getToken(request);
            if (token != null && JwtUtils.verifyToken(token, CommonConstants.TOKEN_SECRET_KEY)) {
                Map<String, Object> claims = JwtUtils.parseToken(token, CommonConstants.TOKEN_SECRET_KEY);
                TokenHolder.setToken(claims);
            } else {
                throw new AuthException();
            }
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("===AuthInterceptor afterCompletion");
        TokenHolder.remove();
        super.afterCompletion(request, response, handler, ex);
    }

    private String getToken(HttpServletRequest request) {
        String token = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        if (token == null) {
            token = request.getHeader("Authorization");
            if (token != null) {
                token = token.substring("token=".length());
            }
        }
        return token;
    }
}
