package com.xyz.spring.boot.xxl.job.controller;

import com.google.gson.internal.LinkedTreeMap;
import com.xxl.job.core.log.XxlJobLogger;
import com.xyz.spring.boot.xxl.job.util.DingTalkUtil;
import com.xyz.spring.boot.xxl.job.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/ownthink")
public class OwnThinkController {

    @Value("${dingding.url}")
    private String durl;

    private String url = "https://api.ownthink.com/bot?appid=xiaosi&userid=user&spoken={0}";

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    private static final String template = "{\n" +
            "     \"msgtype\": \"text\",\n" +
            "     \"text\": {\n" +
            "         \"content\": \"{0}\"},\n" +
            "      \"at\": {\"atMobiles\": [\"{1}\"]}\n" +
            "}";

    @GetMapping("/talk")
    public String talk(@RequestParam("content")String content,@RequestParam("phone")String phone){
        String text = getText(content);
        if(text==null){
            text = "此时无声胜有声";
        }
        DingTalkUtil.send(durl,template.replace("{0}",text).replace("{1}",phone));
        return "success";
    }

    private String getText(String content){
        Request request = new Request.Builder()
                .url(MessageFormat.format(url,content))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            XxlJobLogger.log("调用思知http接口异常",e);
            return "懒得理你";
        }

        if(response==null){
            return null;
        }

        if(!response.isSuccessful()){
            log.error("思知接口响应不成功:{}", GsonUtil.toJson(response));
            return null;
        }else{
            String bodyStr = null;
            try {
                bodyStr = response.body().string();
            }catch (Exception e){
                log.error("思知接口返回信息异常",e);
                return null;
            }

            if(bodyStr!=null){
                try {
                    Map<String, Object> body = GsonUtil.toMap2(bodyStr);
                    LinkedTreeMap data = (LinkedTreeMap) body.get("data");
                    LinkedTreeMap info = (LinkedTreeMap) data.get("info");
                    String text = info.get("text").toString();
                    return text;
                }catch (Exception e){
                    log.error("解析body数据异常{}",bodyStr);
                    return null;
                }
            }

            return null;
        }
    }

    public static void main(String[] args) {
        String url = "https://oapi.dingtalk.com/robot/send?access_token=f1bcffa05a924cb03154200bd5a7ac9327202f802e65af612f05721af31c846c";
        String text = "并且受苦受累";
        String phone = "18966160045";
        DingTalkUtil.send(url,template.replace("{0}",text).replace("{1}",phone));
    }
}
