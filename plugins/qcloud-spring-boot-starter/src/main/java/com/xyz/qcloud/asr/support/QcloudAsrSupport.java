package com.xyz.qcloud.asr.support;

import com.xyz.qcloud.asr.QcloudProperties;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class QcloudAsrSupport {

    private static Logger logger = LoggerFactory.getLogger(QcloudAsrSupport.class);

    private static QcloudProperties qcloudProperties;

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    private static final String baseUrl = "https://aai.qcloud.com/asr/v1/";

    public QcloudAsrSupport(QcloudProperties properties) {
        qcloudProperties = properties;
    }

    public static QcloudResponse executeOfflineAsr(String voiceUrl) {
        QcloudResponse result = new QcloudResponse();

        Map<String, Object> reqMap = paramsMap(voiceUrl);

        String serverUrl = generateUrl(baseUrl, reqMap,true);

        String authInfo = createSign(serverUrl, qcloudProperties.getSecretKey());

        Headers headers = new Headers.Builder()
                .add("Authorization", authInfo)
                .add("Content-Type", "application/octet-stream")
                .add("Host", "aai.qcloud.com")
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),"");

        String reqUrl = generateUrl(baseUrl, reqMap,false);

        Request request = new Request.Builder()
                .url(reqUrl)
                .headers(headers)
                .post(requestBody)
                .build();

        result.setAuthInfo(authInfo);
        result.setRequestUrl(reqUrl);
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            throw new RuntimeException("调用腾讯云离线语音接口异常", e);
        }
        if(response==null){
            return result;
        }

        if(!response.isSuccessful()){
            logger.warn("请求接口返回非200,实际返回code={},message={}", response.code(), response.message());
            try {
                logger.warn("实际返回,body={}", response.body().string());
            }catch (Exception e){
                logger.error("读取Response的body数据异常",e);
            }
            return result;
        }

        String bodyStr = null;
        try {
            bodyStr = response.body().string();
        }catch (Exception e){
            throw new RuntimeException("腾讯云离线语音接口调用返回body解析异常",e);
        }

        if(bodyStr!=null){
            try {
                Map<String, Object> body = GsonUtil.toMap(bodyStr);
                result.setRequestId(String.valueOf(body.get("requestId")));
                return result;
            }catch (Exception e){
                throw new RuntimeException("腾讯云离线语音接口调用返回值获取requestId异常",e);
            }
        }

        return result;
    }

    private static String urlEncode(String url){
        try {
            return URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("url encode error", e);
        }
    }

    private static Map<String, Object> paramsMap(String voiceUrl) {
        Map<String, Object> reqMap = new TreeMap<>();
        reqMap.put("appid", qcloudProperties.getAppId());
        reqMap.put("projectid", qcloudProperties.getProjectId());
        reqMap.put("sub_service_type", "0");
        reqMap.put("engine_model_type", qcloudProperties.getEngineModelType());
        reqMap.put("callback_url", qcloudProperties.getCallbackUrl());
        reqMap.put("channel_num", qcloudProperties.getChannelNum());
        reqMap.put("res_text_format", qcloudProperties.getResTextFormat());
        reqMap.put("res_type", qcloudProperties.getResType());
        reqMap.put("source_type", qcloudProperties.getSourceType());
        reqMap.put("url", voiceUrl);
        reqMap.put("secretid", qcloudProperties.getSecretId());
        reqMap.put("timestamp", toUNIXEpoch());
        reqMap.put("expired", toExpiredUNIXEpoch());
        reqMap.put("nonce", getNonce());
        return reqMap;
    }

    private static String toUNIXEpoch() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime + "";
    }

    private static String toExpiredUNIXEpoch() {
        long unixTime = System.currentTimeMillis() / 1000L + 24 * 60 * 60;
        return unixTime + "";
    }

    private static String getNonce() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(10000) + "";
    }

    private static String generateUrl(String serverUrl, Map<String, Object> mapReq,boolean forSign) {
        StringBuilder strBuilder = new StringBuilder(serverUrl);

        if (mapReq.containsKey("appid")) {
            strBuilder.append(mapReq.get("appid"));
        }

        strBuilder.append('?');

        // to make that all the parameters are sorted by ASC order
        TreeMap<String, String> sortedMap = new TreeMap(mapReq);

        for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
            if (entry.getKey().equals("appid")) {
                continue;
            }
            strBuilder.append(entry.getKey());
            strBuilder.append('=');
            if(forSign) {
                strBuilder.append(entry.getValue());
            }else {
                strBuilder.append(urlEncode(entry.getValue()));
            }
            strBuilder.append('&');
        }

        if (mapReq.size() > 0) {
            strBuilder.setLength(strBuilder.length() - 1);
        }

        return strBuilder.toString();
    }

    private static String createSign(String serverUrl, String secretKey) {
        String strToBeEncoded = "POST" + serverUrl.substring(8);
        return base64_hmac_sha1(strToBeEncoded, secretKey);
    }

    private static String base64_hmac_sha1(String value, String keyStr) {
        String encoded = "";
        String type = "HmacSHA1";
        try {
            byte[] key = (keyStr).getBytes("UTF-8");
            byte[] Sequence = (value).getBytes("UTF-8");

            Mac HMAC = Mac.getInstance(type);
            SecretKeySpec secretKey = new SecretKeySpec(key, type);

            HMAC.init(secretKey);
            byte[] Hash = HMAC.doFinal(Sequence);

            encoded = Base64.getEncoder().encodeToString(Hash);
        } catch (Exception e) {
            throw new RuntimeException("base64_hmac_sha1 error", e);
        }
        return encoded;
    }
}
