package com.xyz.common.base.rabbitmq;

import java.io.Serializable;
import java.util.Date;

public class CustomMQMessage<T> implements Serializable {
    private static final long serialVersionUID = -7966991510061741610L;

    public static int MAX_SENDNUM = 3;
    public String protocol;// 协议类型
    public String title = "";// 消息标题
    public T body;// 消息内容
    private Date sendDate = new Date();
    private int curSendNum = 1;// 发送次数


    public CustomMQMessage() {
    }

    public CustomMQMessage(T body) {
        this.body = body;
    }

    public CustomMQMessage(String protocol, String title) {
        this.protocol = protocol;
        this.title = title;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public int getCurSendNum() {
        return curSendNum;
    }

    public void setCurSendNum(int curSendNum) {
        this.curSendNum = curSendNum;
    }

    public void addSendNum() {
        this.curSendNum++;
    }

    public boolean isAgainSend() {
        return this.curSendNum <= MAX_SENDNUM;
    }

}
