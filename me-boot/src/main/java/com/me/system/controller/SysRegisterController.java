package com.me.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me.common.core.controller.BaseController;
import com.me.common.core.domain.R;
import com.me.common.core.domain.model.RegisterBody;
import com.me.framework.web.service.SysRegisterService;

/**
 * 注册验证
 *
 * me.system
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @PostMapping("/register")
    public R register(@RequestBody RegisterBody user)
    {
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
