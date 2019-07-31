package com.xyz.common.utils.test;

import com.xyz.common.utils.StringUtils;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void getStrList() {
        String str = "其中有个很重要的部分就是通知渠道，包括站内信、短信、邮件等其他方式。而在众多渠道中最重要和必不可少的就是站内信了，毕竟短信、邮件这些触达方式要钱不说，还会分分钟钟被用户吐槽和拉黑。" +
                "需要注意的是：无论在在PC端网站还是APP端的推送，本篇文章都统一称为站内信，它们在底层都是同一套，只是展现方式不同而已。";
        for(String temp: StringUtils.getStrList(str,22)){
            System.out.println(temp);
        }
    }
}