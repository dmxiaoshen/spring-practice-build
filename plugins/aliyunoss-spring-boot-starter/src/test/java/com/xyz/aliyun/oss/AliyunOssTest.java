package com.xyz.aliyun.oss;

import com.aliyun.oss.model.OSSObject;
import com.xyz.aliyun.oss.support.AliyunOSSSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = OSSAutoConfiguration.class)
public class AliyunOssTest {

    @Test
    public void test(){
        URL path = this.getClass().getResource("/");
        File file = new File(path.getFile() + File.separator + "test.png");
        String fileKey3 = AliyunOSSSupport.put(".png", file);
        System.out.println(fileKey3);
    }

    @Test
    public void testPublic() {
        String fileKey1 = AliyunOSSSupport.put(".jpg", "http://img.zcool.cn/community/019c2958a2b760a801219c77a9d27f.jpg");
        String fileKey2 = AliyunOSSSupport.put(".jpg", "http://img.zcool.cn/community/019c2958a2b760a801219c77a9d27f.jpg", x -> {
            return "test_public_9001" + x;
        });
        URL path = this.getClass().getResource("/");
        File file = new File(path.getFile() + File.separator + "test.png");
        String fileKey3 = AliyunOSSSupport.put(".png", file);
        String fileKey4 = AliyunOSSSupport.put(".png", file, x -> {
            return "test_public_9002" + x;
        });
        String fileKey5 = AliyunOSSSupport.put(".png", getInputStreamFromFile(file));
        String fileKey6 = AliyunOSSSupport.put(".png", getInputStreamFromFile(file), x -> {
            return "test_public_9003" + x;
        });
        String fileKey7 = AliyunOSSSupport.put(".png", getBytesFromFile(file));
        String fileKey8 = AliyunOSSSupport.put(".png", getBytesFromFile(file), x -> {
            return "test_public_9004" + x;
        });

        String filePath = path.getFile() + File.separator + "test_public_down.jpg";
        File f = AliyunOSSSupport.getObjectForFile(fileKey2, filePath);
        byte[] data = AliyunOSSSupport.getObjectForByte(fileKey1);
        OSSObject ossObject = AliyunOSSSupport.getObject(fileKey3);

        String url1 = AliyunOSSSupport.getUrl(fileKey4);
        System.out.println("url1=" + url1);
        String url2 = AliyunOSSSupport.getUrl(fileKey5, 1 * 60 * 1000);
        System.out.println("url2=" + url2);
        String url3 = AliyunOSSSupport.getUrl(fileKey6, -1);
        System.out.println("url3=" + url3);
        String url4 = AliyunOSSSupport.getUrl(fileKey7, dateFormat("2018-10-11 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        System.out.println("url4=" + url4);
        System.out.println("aliyun oss test public end");
    }

    @Test
    public void testInternal() {
        String fileKey1 = AliyunOSSSupport.putInternal(".jpg", "http://img.zcool.cn/community/019c2958a2b760a801219c77a9d27f.jpg");
        String fileKey2 = AliyunOSSSupport.putInternal(".jpg", "http://img.zcool.cn/community/019c2958a2b760a801219c77a9d27f.jpg", x -> {
            return "test_internal_9001" + x;
        });
        URL path = this.getClass().getResource("/");
        File file = new File(path.getFile() + File.separator + "test.png");
        String fileKey3 = AliyunOSSSupport.putInternal(".png", file);
        String fileKey4 = AliyunOSSSupport.putInternal(".png", file, x -> {
            return "test_internal_9002" + x;
        });
        String fileKey5 = AliyunOSSSupport.putInternal(".png", getInputStreamFromFile(file));
        String fileKey6 = AliyunOSSSupport.putInternal(".png", getInputStreamFromFile(file), x -> {
            return "test_internal_9003" + x;
        });
        String fileKey7 = AliyunOSSSupport.putInternal(".png", getBytesFromFile(file));
        String fileKey8 = AliyunOSSSupport.putInternal(".png", getBytesFromFile(file), x -> {
            return "test_internal_9004" + x;
        });

        String filePath = path.getFile() + File.separator + "test_internal_down.jpg";
        File f = AliyunOSSSupport.getObjectForFileInternal(fileKey2, filePath);
        byte[] data = AliyunOSSSupport.getObjectForByteInternal(fileKey1);
        OSSObject ossObject = AliyunOSSSupport.getObjectInternal(fileKey3);

        String url1 = AliyunOSSSupport.getUrlInternal(fileKey4);
        System.out.println("url1=" + url1);
        String url2 = AliyunOSSSupport.getUrlInternal(fileKey5, 1 * 60 * 1000);
        System.out.println("url2=" + url2);
        String url3 = AliyunOSSSupport.getUrlInternal(fileKey6, -1);
        System.out.println("url3=" + url3);
        String url4 = AliyunOSSSupport.getUrlInternal(fileKey7, dateFormat("2018-10-11 00:00:00", "yyyy-MM-dd HH:mm:ss"));
        System.out.println("url4=" + url4);
        System.out.println("aliyun oss test internal end");
    }

    private byte[] getBytesFromFile(File file) {
        byte[] ret = null;
        try {
            if (file == null) {
                return null;
            }
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            ret = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private InputStream getInputStreamFromFile(File file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    private Date dateFormat(String dateStr, String pattren) {
        SimpleDateFormat sdt = new SimpleDateFormat(pattren);
        try {
            return sdt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
