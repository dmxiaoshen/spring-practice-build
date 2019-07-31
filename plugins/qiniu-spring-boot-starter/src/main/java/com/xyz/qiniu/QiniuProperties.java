package com.xyz.qiniu;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {

    private String bucket;
    private String prefix;
    private String accessKey;
    private String secretKey;
    private int tokenExpriy = 1*60*60;
    private String policy;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getTokenExpriy() {
        return tokenExpriy;
    }

    public void setTokenExpriy(int tokenExpriy) {
        this.tokenExpriy = tokenExpriy;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }
}
