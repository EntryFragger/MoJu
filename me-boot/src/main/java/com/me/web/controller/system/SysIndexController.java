package com.me.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.common.config.MeConfig;
import com.me.common.utils.StringUtils;

/**
 * 首页
 *
 * me.system
 */
@RestController
public class SysIndexController
{
    /** 系统基础配置 */
    @Autowired
    private MeConfig bdglConfig;

    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("欢迎使用");
    }
}
