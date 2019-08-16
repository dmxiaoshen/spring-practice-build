package com.xyz.designpatterns;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by hzhsg on 2018/5/16.
 */
public class GsonUtil {

    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    public static String toJson(Object obj){
        return GSON.toJson(obj);
    }
}
