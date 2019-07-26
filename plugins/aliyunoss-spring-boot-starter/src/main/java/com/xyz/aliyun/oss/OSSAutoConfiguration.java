package com.xyz.aliyun.oss;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.comm.Protocol;
import com.xyz.aliyun.oss.support.AliyunOSSSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@EnableConfigurationProperties(OSSProperties.class)
@ConditionalOnProperty(prefix = "aliyun-oss", value = "enabled", havingValue = "true", matchIfMissing = true)
public class OSSAutoConfiguration {
    @Autowired
    private OSSProperties ossProperties;

    private OSSClient publicOssClient;

    private OSSClient internalOssClient;

    @PreDestroy
    public void close() {
        if (this.publicOssClient != null) {
            this.publicOssClient.shutdown();
        }
        if (this.internalOssClient != null) {
            this.internalOssClient.shutdown();
        }
    }

    @PostConstruct
    public void init() {
        checkout(ossProperties.getEndpointPublicUrl(), "endpointPublicUrl");
        checkout(ossProperties.getAccessKeyId(), "accessKeyId");
        checkout(ossProperties.getAccessKeySecret(), "accessKeySecret");
        checkout(ossProperties.getBucketName(), "bucketName");
    }

    private void checkout(Object obj, String key) {
        if (obj == null) {
            throw new RuntimeException("${aliyun-oss." + key + "} should not be empty");
        } else {
            if (obj instanceof String) {
                if ("".equals(((String) obj).trim())) {
                    throw new RuntimeException("${aliyun-oss." + key + "} should not be empty");
                }
            }
        }
    }

    @Bean(name = "ossClientConfiguration")
    @ConditionalOnMissingBean
    public ClientConfiguration ossClientConfiguration(OSSProperties ossProperties) {
        OSSProperties.Client client = ossProperties.getClient();
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        // 允许打开的最大HTTP连接数。默认为1024
        clientConfiguration.setMaxConnections(client.getMaxConnections());
        // 请求失败后最大的重试次数。默认3次
        clientConfiguration.setMaxErrorRetry(client.getMaxErrorRetry());
        // Socket层传输数据的超时时间（单位：毫秒）。默认为50000毫秒
        clientConfiguration.setSocketTimeout(client.getSocketTimeout());
        // 建立连接的超时时间（单位：毫秒）。默认为50000毫秒
        clientConfiguration.setConnectionTimeout(client.getConnectionTimeout());
        // 从连接池中获取连接的超时时间（单位：毫秒）。默认不超时
        clientConfiguration.setConnectionRequestTimeout(client.getConnectionRequestTimeout());
        // 如果空闲时间超过此参数的设定值，则关闭连接（单位：毫秒）。默认为60000毫秒
        clientConfiguration.setIdleConnectionTime(client.getIdleConnectionTime());
        // 是否支持CNAME作为Endpoint，默认支持CNAME
        clientConfiguration.setSupportCname(client.isSupportCname());
        // 是否开启二级域名（Second Level Domain）的访问方式，默认不开启
        clientConfiguration.setSLDEnabled(client.isSldEnabled());
        // 连接OSS所采用的协议（HTTP/HTTPS），默认为HTTP
        clientConfiguration.setProtocol(client.getProtocol().equals(Protocol.HTTPS.toString()) ? Protocol.HTTPS : Protocol.HTTP);
        // 用户代理，指HTTP的User-Agent头。默认为”aliyun-sdk-java”
        clientConfiguration.setUserAgent(client.getUserAgent());
        return clientConfiguration;
    }

    @Bean(name = "publicOssClient")
    public OSSClient publicOssClient(OSSProperties ossProperties, ClientConfiguration ossClientConfiguration) {
        publicOssClient = new OSSClient(ossProperties.getEndpointPublicUrl(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret(), ossClientConfiguration);
        return publicOssClient;
    }

    @Bean(name = "internalOssClient")
    public OSSClient internalOssClient(OSSProperties ossProperties, ClientConfiguration ossClientConfiguration) {
        if (ossProperties.getEndpointInternalUrl() == null || "".equals(ossProperties.getEndpointInternalUrl().trim())) {
            internalOssClient = new OSSClient(ossProperties.getEndpointPublicUrl(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret(), ossClientConfiguration);
        } else {
            internalOssClient = new OSSClient(ossProperties.getEndpointInternalUrl(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret(), ossClientConfiguration);
        }
        return internalOssClient;
    }

    @Bean(name = "aliyunOSSSupport")
    public AliyunOSSSupport aliyunOSSSupport(OSSProperties ossProperties, OSSClient publicOssClient, OSSClient internalOssClient) {
        return new AliyunOSSSupport(ossProperties, publicOssClient, internalOssClient);
    }
}
