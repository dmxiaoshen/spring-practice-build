package com.xiaouchina.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    private static final Base64.Encoder ENCODER = Base64.getEncoder();

    private static final String DEFAULT_CHARSET = "UTF-8";


    public static String encoder(String str){
        if(str==null){
            return null;
        }
        byte[] bytes = null;
        try {
            bytes = str.getBytes(DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
           throw new RuntimeException("Base64Utils encoder error",e);
        }
        if(bytes!=null){
            return new String(ENCODER.encodeToString(bytes));
        }
        return null;
    }

    public static String decoder(String str){
        if(str==null){
            return null;
        }
        try {
            return new String(DECODER.decode(str),DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Base64Utils decoder error",e);
        }
    }
}
