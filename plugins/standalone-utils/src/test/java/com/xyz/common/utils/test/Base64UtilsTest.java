package com.xyz.common.utils.test;

import com.xyz.common.utils.Base64Utils;
import org.junit.Test;

public class Base64UtilsTest {

    @Test
    public void encoder() {
        String str="这个是一个base64测试字符串";
        System.out.println(Base64Utils.encoder(str));
    }

    @Test
    public void decoder() {

        System.out.println(Base64Utils.decoder("6L+Z5Liq5piv5LiA5LiqYmFzZTY05rWL6K+V5a2X56ym5Liy"));
    }
}