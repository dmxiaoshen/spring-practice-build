package com.xyz.qcloud.asr.support;

import java.io.Serializable;

public class QcloudResponse implements Serializable {
    private static final long serialVersionUID = -1980018124270254707L;

    private String requestId;

    private String requestUrl;

    private String authInfo;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }
}
