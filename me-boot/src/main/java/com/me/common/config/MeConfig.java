package com.me.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * me.system
 */
@Component
@ConfigurationProperties(prefix = "me")
public class MeConfig
{

    /** 上传路径 */
    private static String profile;


    public static String getProfile()
    {
        return profile;
    }

    public void setProfile(String profile)
    {
        MeConfig.profile = profile;
    }

    /**
     * 获取导入上传路径
     */
    public static String getImportPath()
    {
        return getProfile() + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/upload";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
    /**
     * 二維碼
     */
    public static String getErWeiMaPath()
    {
        return getProfile() + "/QR";
    }
}
