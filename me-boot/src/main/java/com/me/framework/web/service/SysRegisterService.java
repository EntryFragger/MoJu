package com.me.framework.web.service;

import com.me.common.core.domain.entity.SysRole;
import com.me.common.utils.bean.BeanUtils;
import com.me.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.me.common.constant.Constants;
import com.me.common.constant.UserConstants;
import com.me.common.core.domain.entity.SysUser;
import com.me.common.core.domain.model.RegisterBody;
import com.me.common.core.cache.MeCache;
import com.me.common.exception.user.CaptchaException;
import com.me.common.exception.user.CaptchaExpireException;
import com.me.common.utils.SecurityUtils;
import com.me.system.service.ISysUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 注册校验方法
 *
 * me.system
 */
@Component
public class SysRegisterService
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private MeCache meCache;

    @Autowired
    private ISysRoleService roleService;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody)
    {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();


        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "用户密码不能为空";
        }
        else if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            msg = "账户长度必须在2到20个字符之间";
        }
        else if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            msg = "密码长度必须在5到20个字符之间";
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(username)))
        {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        }
        else
        {
            SysUser sysUser = new SysUser();
            BeanUtils.copyBeanProp(sysUser, registerBody);

            sysUser.setUserName(username);
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));

            SysRole role = new SysRole();
            role.setRoleKey("common");
            List<SysRole> roleList = roleService.selectRoleList(role);
            if(!roleList.isEmpty()){
                List<Long> ids = roleList.stream().map(e->e.getRoleId()).collect(Collectors.toList());
                Long[] idss = ids.toArray(new Long[ids.size()]);
                sysUser.setRoleIds(idss);
            }

            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag)
            {
                msg = "注册失败,请联系系统管理人员";
            }
            else
            {
            }
        }
        return msg;
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = (String)meCache.getCacheObject(verifyKey);
        meCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            throw new CaptchaException();
        }
    }
}
