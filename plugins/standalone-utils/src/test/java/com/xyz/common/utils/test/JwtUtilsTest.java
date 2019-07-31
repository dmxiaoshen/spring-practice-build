package com.xyz.common.utils.test;

import com.xyz.common.utils.JacksonUtils;
import com.xyz.common.utils.JwtUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtUtilsTest {

    @Test
    public void buildToken() {
        String secretKey = "hh345";
        Map<String,Object> body = new HashMap<>();
        body.put("userId","13212");
        body.put("admin",false);
        String token = JwtUtils.buildToken(60*60*1000,body,secretKey);
        System.out.println(token);
    }

    @Test
    public void parseToken() {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6ZmFsc2UsImV4cCI6MTU1NjAxNTI4NywidXNlcklkIjoiMTMyMTIiLCJpYXQiOjE1NTYwMTE2ODd9.G-Bsta91z8cJJ9roev5E9pWMQpdzbyQdNlTM2sSYTSQ";
        String secretKey = "hh345";
        Map<String,Object> re = JwtUtils.parseToken(token,secretKey);
        System.out.println(JacksonUtils.toJson(re));
    }

    @Test
    public void verifyToken(){
        String token="eyJhbGciOiJIUzI1NiJ9.eyJhZG1pbiI6ZmFsc2UsImV4cCI6MTU1NjAxNTI4NywidXNlcklkIjoiMTMyMTIiLCJpYXQiOjE1NTYwMTE2ODd9.G-Bsta91z8cJJ9roev5E9pWMQpdzbyQdNlTM2sSYTSQ";
        String token1="eyJhbGciOiJIUzI1NiJ9.eyJhZG1pbiIZmFsc2UsImV4cCI6MTU1NjAxNTI4NywidXNlcklkIjoiMTMyMTIiLCJpYXQiOjE1NTYwMTE2ODd9.G-Bsta91z8cJJ9roev5E9pWMQpdzbyQdNlTM2sSYTSQ";
        String secretKey = "hh345";
        System.out.println(JwtUtils.verifyToken(token,secretKey));
        System.out.println(JwtUtils.verifyToken(token1,secretKey));
    }

    @Test
    public void test(){
        String str = "https://qiniu.xblz.org/tmp_0c6a50f5a9688d222672fa62788c73455994226a394e1250.jpg?imageMogr2/thumbnail/!75p&e=1559129707&token=U691UmTFuzccWD0qzR6A2Xbn5OwzPou_o3UoO8Dt:TmdvSXlKTn8nDCAnuKN673Kciiw=";
        System.out.println(str.substring(0,str.lastIndexOf("?")));
    }
}