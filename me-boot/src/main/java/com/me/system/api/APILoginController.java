package com.me.system.api;

import com.me.common.core.domain.R;
import com.me.common.core.domain.entity.SysUser;
import com.me.common.utils.SecurityUtils;
import com.me.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 登录验证
 *
 * me.system
 */
@RestController
@RequestMapping("/api")
public class APILoginController
{
    @Autowired
    private ISysUserService userService;

    /**
     * 登录方法
     */
    @PostMapping("/login")
    public R login(@RequestBody SysUser u)
    {
        SysUser user = userService.selectUserByUserName(u.getUserName());
        if(user == null){
            return R.error("账号不存在");
        }
        if(!SecurityUtils.matchesPassword(u.getPassword(), user.getPassword())){
            return R.error("密码错误");
        }
        user.setPassword(null);
        return R.success(user);
    }

    @PostMapping("/resetPwd")
    public R resetPwd(@RequestBody SysUser user)
    {
        SysUser select = userService.selectUserById(user.getUserId());
        if(user == null){
            return R.error("账号不存在");
        }
        if(!SecurityUtils.matchesPassword(user.getOldPassword(), select.getPassword())){
            return R.error("旧密码错误");
        }

        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        return R.success(userService.resetPwd(user));
    }

}
