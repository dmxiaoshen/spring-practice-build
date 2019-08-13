package com.xyz.common.base.exception;

public class LockException extends RuntimeException{

    private static final long serialVersionUID = 5881456656480386816L;

    public LockException(){
        this("获取锁失败");
    }

    public LockException(String message){
        super(message);
    }
}