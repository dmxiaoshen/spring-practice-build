package com.xyz.aliyun.oss.support;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.xyz.aliyun.oss.OSSProperties;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AliyunOSSSupport {
    /**oss配置*/
    private static OSSProperties stOssProperties;
    /**oss内网客户端*/
    private static OSSClient stInternalOssClient;
    /**oss外网客户端*/
    private static OSSClient stPublicOssClient;
    /**仓库名称*/
    private static String bucketName;

    /**默认过期毫秒数(10分钟)*/
    private static final int DEFAULT_EXPIRES_TIME = 10 * 60 * 1000;
    /**永久有效过期时间*/
    private static final String EXPIRATION_DATE = "2099-01-01 00:00:00";
    /**默认fileKey生成器*/
    private static FileKeyBuild defaultFileKeyBuild = (suffix) -> {
        return new StringBuilder(FileKeyBuild.uuid()).append(suffix).toString();
    };

    public AliyunOSSSupport(OSSProperties ossProperties, OSSClient publicOssClient, OSSClient internalOssClient) {
        stOssProperties = ossProperties;
        stPublicOssClient = publicOssClient;
        stInternalOssClient = internalOssClient;
        bucketName = ossProperties.getBucketName();
    }

    /**
     * 获取当前仓库名称
     *
     * @return
     */
    public static String getBucketName() {
        return bucketName;
    }

    /**
     * 文件后缀及url存入oss,公网
     * @param suffix
     * @param imageUrl
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix, String imageUrl) {
        return put(suffix,imageUrl,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及url存入oss,公网
     * @param suffix
     * @param imageUrl
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix, String imageUrl, FileKeyBuild fileKeyBuild){
        return put(suffix,imageUrl,fileKeyBuild,stPublicOssClient);
    }

    /**
     * 文件后缀及url存入oss,内网
     * @param suffix
     * @param imageUrl
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix,String imageUrl){
        return putInternal(suffix,imageUrl,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及url存入oss,内网
     * @param suffix
     * @param imageUrl
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix, String imageUrl, FileKeyBuild fileKeyBuild){
        return put(suffix,imageUrl,fileKeyBuild,stInternalOssClient);
    }

    /**
     * 通过url上传文件
     * @param suffix 文件后缀名 如 .jpg .png
     * @param imageUrl 文件url地址,一般只图片
     * @param fileKeyBuild fileKey生成器
     * @param ossClient oss客户端对象
     * @return 文件存于oss上的fileKey
     */
    private static String put(String suffix, String imageUrl, FileKeyBuild fileKeyBuild,OSSClient ossClient) {
        try {
            String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
            InputStream inputStream = new URL(imageUrl).openStream();
            ossClient.putObject(bucketName, fileKey, inputStream);
            return fileKey;
        } catch (IOException e) {
            throw new RuntimeException("oss the imageUrl not support", e);
        } catch (Exception e) {
            throw new RuntimeException("oss put[suffix,imageUrl] error", e);
        }
    }

    /**
     * 文件后缀及byte数组存入oss,公网
     * @param suffix
     * @param data
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix, byte[] data) {
        return put(suffix,data,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及byte数组存入oss,公网
     * @param suffix
     * @param data
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix,byte[] data,FileKeyBuild fileKeyBuild){
        return put(suffix,data,fileKeyBuild,stPublicOssClient);
    }

    /**
     * 文件后缀及byte数组存入oss,内网
     * @param suffix
     * @param data
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix,byte[] data){
        return putInternal(suffix,data,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及byte数组存入oss,内网
     * @param suffix
     * @param data
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix,byte[] data,FileKeyBuild fileKeyBuild){
        return put(suffix,data,fileKeyBuild,stInternalOssClient);
    }

    /**
     * 文件后缀及byte数组存入oss
     * @param suffix 文件后缀名 如 .jpg .png
     * @param data byte数组
     * @param fileKeyBuild fileKey生成器
     * @param ossClient oss客户端对象
     * @return 文件存于oss上的fileKey
     */
    private static String put(String suffix,byte[] data,FileKeyBuild fileKeyBuild,OSSClient ossClient){
        try {
            String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
            ossClient.putObject(bucketName, fileKey, new ByteArrayInputStream(data));
            return fileKey;
        } catch (Exception e) {
            throw new RuntimeException("oss put[suffix,byte] error", e);
        }
    }

    /**
     * 文件后缀及inputStream流存入oss,公网
     * @param suffix
     * @param input
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix,InputStream input){
        return put(suffix,input,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及inputStream流存入oss,公网
     * @param suffix
     * @param input
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix,InputStream input,FileKeyBuild fileKeyBuild){
        return put(suffix,input,fileKeyBuild,stPublicOssClient);
    }

    /**
     * 文件后缀及inputStream流存入oss,内网
     * @param suffix
     * @param input
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix,InputStream input){
        return putInternal(suffix,input,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及inputStream流存入oss,内网
     * @param suffix
     * @param input
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix,InputStream input,FileKeyBuild fileKeyBuild){
        return put(suffix,input,fileKeyBuild,stInternalOssClient);
    }

    /**
     * 文件后缀及inputStream流存入oss
     * @param suffix 文件后缀名 如 .jpg .png
     * @param input 输入流
     * @param fileKeyBuild fileKey生成器
     * @param ossClient oss客户端对象
     * @return 文件存于oss上的fileKey
     */
    private static String put(String suffix,InputStream input,FileKeyBuild fileKeyBuild,OSSClient ossClient){
        try{
            String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
            ossClient.putObject(bucketName,fileKey,input);
            return fileKey;
        }catch (Exception e){
            throw new RuntimeException("oss put[suffix,inputstream] error",e);
        }
    }


    /**
     * 文件后缀及本地文件存入oss,公网
     * @param suffix
     * @param file
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix, File file) {
        return put(suffix,file,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及本地文件存入oss,公网
     * @param suffix
     * @param file
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String put(String suffix, File file,FileKeyBuild fileKeyBuild){
        return put(suffix,file,fileKeyBuild,stPublicOssClient);
    }

    /**
     * 文件后缀及本地文件存入oss,内网
     * @param suffix
     * @param file
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix,File file){
        return putInternal(suffix,file,defaultFileKeyBuild);
    }

    /**
     * 文件后缀及本地文件存入oss,内网
     * @param suffix
     * @param file
     * @param fileKeyBuild 自定义fileKey生成规则
     * @return 文件存于oss上的fileKey
     */
    public static String putInternal(String suffix, File file,FileKeyBuild fileKeyBuild){
        return put(suffix,file,fileKeyBuild,stInternalOssClient);
    }

    /**
     * 文件后缀及本地文件存入oss
     * @param suffix 文件后缀名 如 .jpg .png
     * @param file 本地文件
     * @param fileKeyBuild fileKey生成器
     * @param ossClient oss客户端对象
     * @return 文件存于oss上的fileKey
     */
    private static String put(String suffix, File file,FileKeyBuild fileKeyBuild,OSSClient ossClient) {
        try {
            String fileKey = fileKeyBuild.build(adaptSuffix(suffix));
            ossClient.putObject(bucketName, fileKey, file);
            return fileKey;
        } catch (Exception e) {
            throw new RuntimeException("oss put[suffix,file] error", e);
        }
    }

    /**
     * 通过fileKey下载oss文件到本地指定路径文件,公网
     * @param fileKey
     * @param filePath
     * @return
     */
    public static File getObjectForFile(String fileKey, String filePath){
        return getObjectForFile(fileKey,filePath,stPublicOssClient);
    }

    /**
     * 通过fileKey下载oss文件到本地指定路径文件,内网
     * @param fileKey
     * @param filePath
     * @return
     */
    public static File getObjectForFileInternal(String fileKey,String filePath){
        return getObjectForFile(fileKey,filePath,stInternalOssClient);
    }

    /**
     * 通过fileKey下载oss文件到本地指定路径文件
     * @param fileKey
     * @param filePath
     * @param ossClient oss客户端对象
     * @return
     */
    private static File getObjectForFile(String fileKey, String filePath,OSSClient ossClient) {
        try {
            File file = new File(filePath);
            ossClient.getObject(new GetObjectRequest(bucketName, fileKey), file);
            return file;
        } catch (Exception e) {
            throw new RuntimeException("oss getObjectForFile[fileKey,filePath] error", e);
        }
    }

    /**
     * 通过fileKey获取oss文件的byte数组,公网
     * @param fileKey
     * @return
     */
    public static byte[] getObjectForByte(String fileKey){
        return getObjectForByte(fileKey,stPublicOssClient);
    }

    /**
     * 通过fileKey获取oss文件的byte数组,内网
     * @param fileKey
     * @return
     */
    public static byte[] getObjectForByteInternal(String fileKey){
        return getObjectForByte(fileKey,stInternalOssClient);
    }

    /**
     * 通过fileKey获取oss文件的byte数组
     * @param fileKey
     * @param ossClient oss客户端对象
     * @return
     */
    private static byte[] getObjectForByte(String fileKey,OSSClient ossClient) {
        try {
            OSSObject ossObject = ossClient.getObject(bucketName, fileKey);
            return toByteArray(ossObject.getObjectContent());
        } catch (Exception e) {
            throw new RuntimeException("oss getObjectForByte[filekey] error", e);
        }
    }

    /**
     * 通过fileKey获取OSSObject对象,公网
     * @param fileKey
     * @return
     */
    public static OSSObject getObject(String fileKey){
        return getObject(fileKey,stPublicOssClient);
    }

    /**
     * 通过fileKey获取OSSObject对象,内网
     * @param fileKey
     * @return
     */
    public static OSSObject getObjectInternal(String fileKey){
        return getObject(fileKey,stInternalOssClient);
    }

    /**
     * 通过fileKey获取OSSObject对象
     * @param fileKey
     * @param ossClient oss客户端对象
     * @return
     */
    private static OSSObject getObject(String fileKey,OSSClient ossClient) {
        try {
            return ossClient.getObject(bucketName, fileKey);
        } catch (Exception e) {
            throw new RuntimeException("oss getObject[fileKey] error", e);
        }
    }

    /**
     * 通过fileKey获取url链接,默认失效时间10分钟,公网
     *
     * @param fileKey
     * @return
     */
    public static String getUrl(String fileKey) {
        return getUrl(fileKey, DEFAULT_EXPIRES_TIME);
    }

    /**
     * 通过fileKey获取url链接,默认失效时间10分钟,内网
     * @param fileKey
     * @return
     */
    public static String getUrlInternal(String fileKey){
        return getUrlInternal(fileKey,DEFAULT_EXPIRES_TIME);
    }

    /**
     * 通过fileKey及传入的实现时间(毫秒),获取url链接,公网
     *
     * @param fileKey
     * @param expiresTime 传大于0的值或者-1,-1表示永久有效
     * @return url链接
     */
    public static String getUrl(String fileKey, int expiresTime) {
        return generateUrl(fileKey, converExpires(expiresTime), false,stPublicOssClient);
    }

    /**
     * 通过fileKey及传入的实现时间(毫秒),获取url链接,内网
     * @param fileKey
     * @param expiresTime 传大于0的值或者-1,-1表示永久有效
     * @return url链接
     */
    public static String getUrlInternal(String fileKey, int expiresTime) {
        return generateUrl(fileKey, converExpires(expiresTime), false,stInternalOssClient);
    }

    /**
     * 通过fileKey及传入的过期时间,获取url链接，公网
     * @param fileKey
     * @param expiration 过期时间应大于当前时间
     * @return url链接
     */
    public static String getUrl(String fileKey, Date expiration) {
        return generateUrl(fileKey, expiration, true,stPublicOssClient);
    }

    /**
     * 通过fileKey及传入的过期时间,获取url链接，内网
     * @param fileKey
     * @param expiration 过期时间应大于当前时间
     * @return url链接
     */
    public static String getUrlInternal(String fileKey, Date expiration) {
        return generateUrl(fileKey, expiration, true,stInternalOssClient);
    }

    /**
     * fileKey和expiration日期获取url链接
     *
     * @param fileKey
     * @param expiration
     * @param validated  是否验证日期
     * @param ossClient oss客户端对象
     * @return url链接
     */
    private static String generateUrl(String fileKey, Date expiration, boolean validated,OSSClient ossClient) {
        if (validated) {
            if (expiration == null || expiration.before(new Date())) {
                throw new RuntimeException("oss getUrl expiration date should not be null or before current time");
            }
        }
        URL url = ossClient.generatePresignedUrl(bucketName, fileKey, expiration);
        return url.toString();
    }

    /**
     * 过期时间毫秒转为一个具体的日期时间
     * @param expiresTime -1则转为给定的永久有效日期
     * @return
     */
    private static Date converExpires(int expiresTime){
        Date expires = null;
        if (expiresTime < 0) {
            if(expiresTime == -1){
                expires = dateFormat(EXPIRATION_DATE,"yyyy-MM-dd HH:mm:ss");
            }else{
                throw new RuntimeException("oss getUrl expiresTime couldn't be less than 0 or -1 is accepted");
            }
        }else{
            expires = new Date(new Date().getTime() + expiresTime);
        }
        return expires;
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

    /**
     * 文件后缀名适配,如 jpg 变成 .jpg
     * @param suffix
     * @return
     */
    private static String adaptSuffix(String suffix){
        if(suffix==null || "".equals(suffix.trim())){
            throw new RuntimeException("oss suffix should not be empty");
        }
        if (suffix != null && suffix.charAt(0) != '.'){
            suffix = "."+suffix;
        }
        return suffix;
    }

    /**
     * 字符串日期根据pattern转成日期对象
     * @param dateStr
     * @param pattren
     * @return
     */
    private static Date dateFormat(String dateStr, String pattren) {
        SimpleDateFormat sdt = new SimpleDateFormat(pattren);
        try {
            return sdt.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("oss dateFormat error",e);
        }
    }
}
