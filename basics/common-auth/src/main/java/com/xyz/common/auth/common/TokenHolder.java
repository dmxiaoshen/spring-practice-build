package com.xyz.common.auth.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class TokenHolder {

    private static final ThreadLocal<Map<String,Object>> tokenLocal = new ThreadLocal<>();

    public static void setToken(Map<String,Object> token) {
        tokenLocal.set(token);
    }

    public static Map<String,Object> getToken() {
        return tokenLocal.get();
    }

    public static void remove() {
        if (getToken() != null) {
            tokenLocal.remove();
        }
    }
}
