package com.xyz.spring.boot.xxl.job.util;

import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public class DingTalkUtil {

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    public static boolean send(String url,String body){
        if(body==null){
            return false;
        }
        Headers headers = new Headers.Builder()
                .add("cache-control", "no-cache")
                .add("Content-Type", "application/json")
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),body);

        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .post(requestBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            XxlJobLogger.log("调用钉钉http接口异常",e);
            return false;
        }
        return true;
    }
}
