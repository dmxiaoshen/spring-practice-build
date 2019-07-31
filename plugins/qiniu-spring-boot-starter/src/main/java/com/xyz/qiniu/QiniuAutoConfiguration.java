package com.xyz.qiniu;

import com.xyz.qiniu.support.QiniuSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(QiniuProperties.class)
@ConditionalOnProperty(prefix = "qiniu", value = "enabled", havingValue = "true", matchIfMissing = true)
public class QiniuAutoConfiguration {

    @Autowired
    private QiniuProperties qiniuProperties;

    @Bean(name = "qiniuSupport")
    public QiniuSupport qiniuSupport(QiniuProperties qiniuProperties){
        return new QiniuSupport(qiniuProperties);
    }
}
