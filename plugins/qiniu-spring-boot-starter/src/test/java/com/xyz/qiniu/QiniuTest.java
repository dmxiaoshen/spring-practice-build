package com.xyz.qiniu;

import com.xyz.qiniu.support.GsonUtil;
import com.xyz.qiniu.support.QiniuSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = QiniuAutoConfiguration.class)
public class QiniuTest {

    @Test
    public void test() {
        String name = QiniuSupport.getBucketName();
        System.out.println(name);
        QiniuProperties p = QiniuSupport.getQiniuProperties();
        System.out.println(p.getPolicy());
        Map<String, Object> objectMap = GsonUtil.toMap(p.getPolicy());
        System.out.println(objectMap.size());

        String url = QiniuSupport.put(".jpg", "http://imgsrc.baidu.com/imgad/pic/item/bba1cd11728b47104c5c00e9c9cec3fdfc0323a0.jpg");
        System.out.println(QiniuSupport.privateUrl(url));
    }

    @Test
    public void testPut() {
        URL path = this.getClass().getResource("/");
        File file = new File(path.getFile() + File.separator + "test.png");
        String url = QiniuSupport.put(".png", file);
        //url = url+"?imageslim";
        url = url +"?imageMogr2/thumbnail/!75p";
        System.out.println(QiniuSupport.privateUrl(url));
    }

    @Test
    public void testWav(){
        File file = new File("D:/a8e3825021f740838ddc75a4f99e0c6d.html");
        String url = QiniuSupport.put(".html",file);
        System.out.println(QiniuSupport.privateUrl(url));
    }

    @Test
    public void testPrivateUrl(){
        String x = "https://qiniu.xblz.org/803420faac944cb4ae0d08d9124b2f34.png";
        String url = "https://qiniu.xblz.org/tmp/wxbbe53b72be5e953b.o6zAJs3pX3l9eyN0TRpAwBEYvCYI.UaptCxwNCwV06937bf73a21eece6d6cd71e627db6b8f.durationTime=1395.mp3";
        System.out.println(QiniuSupport.privateUrl(x));
    }

    @Test
    public void sign(){
        File file = new File("D:/temThirdServerSeal.png");
        String url = QiniuSupport.put(".png",file,x->{return "temThirdServerSeal"+x;});
        System.out.println(QiniuSupport.privateUrl(url));
    }
}
