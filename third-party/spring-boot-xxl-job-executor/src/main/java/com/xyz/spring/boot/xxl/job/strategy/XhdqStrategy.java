package com.xyz.spring.boot.xxl.job.strategy;

import com.google.gson.internal.LinkedTreeMap;
import com.xxl.job.core.log.XxlJobLogger;
import com.xyz.spring.boot.xxl.job.util.DingTalkUtil;
import com.xyz.spring.boot.xxl.job.util.GsonUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class XhdqStrategy extends AbstractStrategy {

    private static String url = "http://v.juhe.cn/joke/randJoke.php?key=747e2151aa73abb2115757ac604181b1";

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

   // private static final String template = "{\n     \"msgtype\": \"text\",\n     \"text\": {\n         \"content\": \"{0}\"}\n}";

    private static final String template = "{\n     \"msgtype\": \"markdown\",\n     \"markdown\": {\n         \"title\":\"笑话大全\",\n         \"text\": \"#### 笑话大全\\n> {0}\\n\"\n     }\n }";

    public XhdqStrategy(String name) {
        super(name);
    }

    @Override
    public String buildBody() {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            XxlJobLogger.log("请求笑话大全接口异常", e);
            return null;
        }
        if (response == null) {
            return null;
        }
        if (!response.isSuccessful()) {
            XxlJobLogger.log("笑话大全接口响应不成功:{}", GsonUtil.toJson(response));
            return null;
        } else {
            String bodyStr = null;
            try {
                bodyStr = response.body().string();
            } catch (Exception e) {
                XxlJobLogger.log("解析笑话大全接口返回信息异常", e);
                return null;
            }

            if (bodyStr != null) {
                try {
                    Map<String, Object> body = GsonUtil.toMap2(bodyStr);
                    List<LinkedTreeMap> list = (List<LinkedTreeMap>) body.get("result");
                    String content = list.get(0).get("content").toString();
                    return template.replace("{0}", content);
                } catch (Exception e) {
                    XxlJobLogger.log("解析body数据异常{}", bodyStr);
                    return null;
                }
            }

            return null;
        }
    }

    public static void main(String[] args) {
        XhdqStrategy xhdqStrategy = new XhdqStrategy("xhdq");
        String x = xhdqStrategy.buildBody();
        System.out.println(x);
        DingTalkUtil.send("https://oapi.dingtalk.com/robot/send?access_token=f1bcffa05a924cb03154200bd5a7ac9327202f802e65af612f05721af31c846c", x);
    }
}
