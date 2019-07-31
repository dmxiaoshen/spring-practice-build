package com.xyz.qiniu.support;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.xyz.qiniu.QiniuProperties;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

public class QiniuSupport {

    /**七牛配置*/
    private static QiniuProperties qiniuProperties;
    /**仓库名称*/
    private static String bucketName;
    /**url默认过期秒数(60分钟)*/
    private static final int DEFAULT_EXPIRES_TIME = 60 * 60;
    /**永久有效过期时间*/
    private static final String EXPIRATION_DATE = "2099-01-01 00:00:00";
    /**永久有效日(根据上面的时间生成)*/
    private static Date expirationDate;
    /**默认fileKey生成器*/
    private static FileKeyBuild defaultFileKeyBuild = (suffix) -> {
        return new StringBuilder(FileKeyBuild.uuid()).append(suffix).toString();
    };

    public QiniuSupport(QiniuProperties properties){
        qiniuProperties = properties;
        bucketName = properties.getBucket();
        expirationDate = DateUtil.dateFormat(EXPIRATION_DATE,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getBucketName(){
        return bucketName;
    }

    public static QiniuProperties getQiniuProperties(){
        return qiniuProperties;
    }

    private static Auth getAuth() {
        return Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
    }

    /**
     * 获取上传策略
     * @return
     */
    private static Map<String,Object> getPolicy(){
        if(qiniuProperties.getPolicy()==null || qiniuProperties.getPolicy().isEmpty()
                || "*".equals(qiniuProperties.getPolicy())){
            return null;
        }
        Map<String,Object> policy = null;
        try{
            policy = GsonUtil.toMap(qiniuProperties.getPolicy());
        }catch(Exception e){
            throw new RuntimeException("七牛获取上传策略异常",e);
        }
        return policy;
    }

    /**
     * 获取七牛token给web端
     * @return
     */
    public static String getUptoken(){
        Map<String,Object> policy = getPolicy();
        StringMap pol = policy==null?null:new StringMap().putAll(policy);
        return getAuth().uploadToken(bucketName, null, qiniuProperties.getTokenExpriy(), pol, true);
    }

    /**
     * 文件后缀名适配,如 jpg 变成 .jpg
     * @param suffix
     * @return
     */
    private static String adaptSuffix(String suffix){
        if(suffix==null || "".equals(suffix.trim())){
            throw new RuntimeException("qiniu suffix should not be empty");
        }
        if (suffix != null && suffix.charAt(0) != '.'){
            suffix = "."+suffix;
        }
        return suffix;
    }

    public static String urlToKey(String url) {
        if(url == null) return null;
        return url.substring(url.lastIndexOf("/") + 1);
    }

    public static String keyToUrl(String key) {
        if(key == null) return null;
        StringBuilder sb = new StringBuilder(qiniuProperties.getPrefix());
        if (qiniuProperties.getPrefix().lastIndexOf("/") ==qiniuProperties.getPrefix().length() - 1) {
            return sb.append(key).toString();
        }
        return sb.append("/").append(key).toString();
    }

    public static String privateUrl(String url){
        return getAuth().privateDownloadUrl(url,DEFAULT_EXPIRES_TIME);
    }

    public static String privateUrl(String url,String params){
        if(params==null || "".equals(params.trim())){
            return privateUrl(url);
        }else{
            url = params.startsWith("?")?url+params:url+"?"+params;
            return privateUrl(url);
        }
    }

    public static String put(String suffix, File file){
        return put(suffix,file,defaultFileKeyBuild);
    }

    public static String put(String suffix,File file,FileKeyBuild fileKeyBuild){
        String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
        try {
            new UploadManager().put(file,fileKey,getAuth().uploadToken(bucketName));
        } catch (QiniuException e) {
            throw new RuntimeException("七牛文件上传异常",e);
        }
        return keyToUrl(fileKey);
    }

    public static String put(String suffix,byte[] data){
        return put(suffix,data,defaultFileKeyBuild);
    }

    public static String put(String suffix,byte[] data,FileKeyBuild fileKeyBuild){
        String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
        try {
            new UploadManager().put(data,fileKey,getAuth().uploadToken(bucketName));
        } catch (QiniuException e) {
            throw new RuntimeException("七牛文件上传异常",e);
        }
        return keyToUrl(fileKey);
    }

    public static String put(String suffix, InputStream inputStream){
        return put(suffix,inputStream,defaultFileKeyBuild);
    }

    public static String put(String suffix,InputStream inputStream,FileKeyBuild fileKeyBuild){
        String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
        try {
            new UploadManager().put(toByteArray(inputStream),fileKey,getAuth().uploadToken(bucketName));
        } catch (Exception e) {
            throw new RuntimeException("七牛文件上传异常",e);
        }
        return keyToUrl(fileKey);
    }

    public static String put(String suffix,String url){
        return put(suffix,url,defaultFileKeyBuild);
    }

    public static String put(String suffix,String url,FileKeyBuild fileKeyBuild){
        String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
        BucketManager bucketManager = new BucketManager(getAuth());
        try {
            bucketManager.fetch(url,bucketName,fileKey);
        } catch (QiniuException e) {
            throw new RuntimeException("七牛fetch文件异常",e);
        }
        return keyToUrl(fileKey);
    }

    /**
     * InputStream流转为byte数组
     *
     * @param input
     * @return
     * @throws IOException
     */
    private static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
