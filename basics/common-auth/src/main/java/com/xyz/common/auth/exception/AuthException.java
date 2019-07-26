package com.xyz.common.auth.exception;

public class AuthException extends RuntimeException{
    private static final long serialVersionUID = 1838441923784690198L;

    public AuthException(){
        this("用户无权限");
    }

    public AuthException(String message){
        super(message);
    }
}
