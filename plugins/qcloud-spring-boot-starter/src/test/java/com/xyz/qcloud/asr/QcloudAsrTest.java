package com.xyz.qcloud.asr;

import com.xyz.qcloud.asr.support.QcloudAsrSupport;
import com.xyz.qcloud.asr.support.QcloudResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = QcloudAutoConfiguration.class)
public class QcloudAsrTest {

    @Test
    public void test(){
        String url = "https://qiniu.xblz.org/tmp_eb922cfce9d46c2a16da6165692b8517.mp3?e=1561181500&token=U691UmTFuzccWD0qzR6A2Xbn5OwzPou_o3UoO8Dt:WcT-RgBkCUHZFx9N0kJ_y8kWxPA=";
        String url2 = "https://qiniu.xblz.org/tmp_eb922cfce9d46c2a16da6165692b8517.mp3?e=1561184214&token=U691UmTFuzccWD0qzR6A2Xbn5OwzPou_o3UoO8Dt:1pl-UGMvfCLjEbcUiXM_6zrvVvA=";
        String url3 = "https://qiniu.xblz.org/tmp_df86e5cd72d1cd27aa0bacc3a9f179f7.mp3?e=1562051763&token=U691UmTFuzccWD0qzR6A2Xbn5OwzPou_o3UoO8Dt:Jv_H1VZED0MgXxK0A6vQC69Udrg=";
        QcloudResponse response = QcloudAsrSupport.executeOfflineAsr(url3);
        System.out.println(response.getRequestId());
    }

    @Test
    public void test1(){
        String str = "{\"audioUrl\":\"https://qiniu.xblz.org/tmp_eb922cfce9d46c2a16da6165692b8517.mp3?e=1562047512&token=U691UmTFuzccWD0qzR6A2Xbn5OwzPou_o3UoO8Dt:z_bxEPfK94DjYHM-mJrXIO_s8tE=\",\"code\":\"0\",\"audioTime\":\"10.588900\",\"requestId\":\"468049799\",\"appid\":\"1258982530\",\"text\":\"[0:0.650,0:1.540]  喂。\\n[0:1.540,0:3.260]  这个是。\\n[0:3.260,0:6.705]  语音内容的测试，主要是为了测试。\\n[0:6.705,0:8.095]  腾讯云。\\n[0:8.095,0:10.450]  语音转文字的准确性。\\n\",\"message\":\"成功\",\"projectid\":\"0\"}";
        String s = "[0:0.650,0:1.540]  喂。\\n[0:1.540,0:3.260]  这个是。\\n[0:3.260,0:6.705]  语音内容的测试，主要是为了测试。\\n[0:6.705,0:8.095]  腾讯云。\\n[0:8.095,0:10.450]  语音转文字的准确性。\\n";
        String x = s.replace("\\n","");
                String y = x.replaceAll("\\[(.+?)\\]","");
                        String z = y.replaceAll("[ \\t]","");
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        String q = s.replace("\\n","").replaceAll("\\[(.+?)\\]","").replaceAll("[ \\t]","");
        System.out.println(q);
    }
}
