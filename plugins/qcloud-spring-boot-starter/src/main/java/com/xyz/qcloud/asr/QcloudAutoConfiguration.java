package com.xyz.qcloud.asr;

import com.xyz.qcloud.asr.support.QcloudAsrSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(QcloudProperties.class)
@ConditionalOnProperty(prefix = "qcloud", value = "enabled", havingValue = "true", matchIfMissing = true)
public class QcloudAutoConfiguration {

    @Autowired
    private QcloudProperties qcloudProperties;

    @Bean(name = "qcloudAsrSupport")
    public QcloudAsrSupport qcloudAsrSupport(QcloudProperties qcloudProperties){
        return new QcloudAsrSupport(qcloudProperties);
    }
}
