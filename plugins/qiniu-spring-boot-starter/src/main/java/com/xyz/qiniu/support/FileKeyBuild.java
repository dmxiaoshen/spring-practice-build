package com.xyz.qiniu.support;

import java.util.UUID;

@FunctionalInterface
public interface FileKeyBuild {

    /**
     * 构建fileKey
     * @param suffix  文件后缀名
     * @return fileKey
     */
    String build(String suffix);

    /**
     * 默认已uuid生成fileKey
     * @return
     */
    static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
