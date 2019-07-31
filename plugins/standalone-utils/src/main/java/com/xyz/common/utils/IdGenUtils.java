package com.xyz.common.utils;

import java.util.UUID;

public class IdGenUtils {

    public static String uuid32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
