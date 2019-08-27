package com.xyz.spring.boot.xxl.job.strategy;

import com.google.gson.internal.LinkedTreeMap;
import com.xxl.job.core.log.XxlJobLogger;
import com.xyz.spring.boot.xxl.job.util.DateUtils;
import com.xyz.spring.boot.xxl.job.util.DingTalkUtil;
import com.xyz.spring.boot.xxl.job.util.GsonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class TodayHisStrategy extends AbstractStrategy {

    private static String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=b82242df1e1c35e3f841bffb3224ee84&date={0}";

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    // private static final String template = "{\n     \"msgtype\": \"text\",\n     \"text\": {\n         \"content\": \"{0}\"}\n}";

    private static final String template = "{\n     \"msgtype\": \"markdown\",\n     \"markdown\": {\n         \"title\":\"历史上的今天\",\n         \"text\": \"#### 历史上的今天\\n> {0}\\n\"\n     }\n }";

    public TodayHisStrategy(String name) {
        super(name);
    }

    @Override
    public String buildBody() {
        Date now = new Date();
        String day = DateUtils.format(now,"M/d");
        Request request = new Request.Builder()
                .url(MessageFormat.format(url,day))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            XxlJobLogger.log("请求历史上的今天接口异常", e);
            return null;
        }
        if (response == null) {
            return null;
        }
        if (!response.isSuccessful()) {
            XxlJobLogger.log("历史上的今天接口响应不成功:{}", GsonUtil.toJson(response));
            return null;
        } else {
            String bodyStr = null;
            try {
                bodyStr = response.body().string();
            } catch (Exception e) {
                XxlJobLogger.log("解析历史上的今天接口返回信息异常", e);
                return null;
            }

            if (bodyStr != null) {
                try {
                    Map<String, Object> body = GsonUtil.toMap2(bodyStr);
                    List<LinkedTreeMap> list = (List<LinkedTreeMap>) body.get("result");
                    StringBuilder buffer = new StringBuilder("");
                    String content = null;
                    for(LinkedTreeMap t : list){
                        buffer.append("【").append(t.get("date")).append("】\\n> ").append(t.get("title")).append("\\n\\n> ");
                    }
                    if("".equals(buffer.toString())){
                        content = "无历史事件";
                    }else{
                        content = buffer.toString();
                    }
                    return template.replace("{0}",content);
                } catch (Exception e) {
                    XxlJobLogger.log("解析body数据异常{}", bodyStr);
                    return null;
                }
            }

            return null;
        }
    }

    public static void main(String[] args) {
        TodayHisStrategy todayHisStrategy = new TodayHisStrategy("lssdjt");
        String x = todayHisStrategy.buildBody();
        System.out.println(x);
        //x = "{\n     \"msgtype\": \"markdown\",\n     \"markdown\": {\n         \"title\":\"历史上的今天\",\n         \"text\": \"#### 历史上的今天\\n> 【1565年8月21号 】\\n> 岳家军大胜利\\n\\n> 【1565年8月21号】 \\n> 岳家军大胜利\\n\\n\"\n     }\n }";
        DingTalkUtil.send("https://oapi.dingtalk.com/robot/send?access_token=8fe0fe7f56b0028d81152ff98ce7922ecea05036c27c55fda5dfa2df04437006", x);
    }


}
