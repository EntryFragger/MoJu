package com.me.common.core.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.me.common.core.domain.entity.SysRole;
import com.me.common.core.domain.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.me.common.constant.HttpStatus;
import com.me.common.core.domain.R;
import com.me.common.core.domain.model.LoginUser;
import com.me.common.core.page.PageDomain;
import com.me.common.core.page.TableDataInfo;
import com.me.common.core.page.TableSupport;
import com.me.common.utils.DateUtils;
import com.me.common.utils.SecurityUtils;
import com.me.common.utils.StringUtils;
import com.me.common.utils.sql.SqlUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * web层通用数据处理
 *
 * me.system
 */
public class BaseController
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    protected boolean isAdmin(){
        Set<String> admSet = new HashSet<>();
        admSet.add("admin");// 管理员标识
        admSet.add("super");
        SysUser nowUser = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = nowUser.getRoles();
        boolean flag = false;
        for(SysRole role : roles){
            if(role.isAdmin() || admSet.contains(role.getRoleKey())){
                flag = true;
                break;
            }
        }
        return flag;
    }
    protected boolean isCommon(){
        Set<String> roleSet = new HashSet<>();
        roleSet.add("common");
        SysUser nowUser = SecurityUtils.getLoginUser().getUser();
        List<SysRole> roles = nowUser.getRoles();
        boolean flag = false;
        for(SysRole role : roles){
            if(roleSet.contains(role.getRoleKey())){
                flag = true;
                break;
            }
        }
        return flag;
    }


    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 返回成功
     */
    public R success()
    {
        return R.success();
    }

    /**
     * 返回失败消息
     */
    public R error()
    {
        return R.error();
    }

    /**
     * 返回成功消息
     */
    public R success(String message)
    {
        return R.success(message);
    }

    /**
     * 返回失败消息
     */
    public R error(String message)
    {
        return R.error(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R toAjax(int rows)
    {
        return rows > 0 ? R.success() : R.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected R toAjax(boolean result)
    {
        return result ? success() : error();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }

    /**
     * 获取用户缓存信息
     */
    public LoginUser getLoginUser()
    {
        return SecurityUtils.getLoginUser();
    }

    /**
     * 获取登录用户id
     */
    public Long getUserId()
    {
        return getLoginUser().getUserId();
    }

    /**
     * 获取登录部门id
     */
    public Long getDeptId()
    {
        return getLoginUser().getDeptId();
    }

    /**
     * 获取登录用户名
     */
    public String getUsername()
    {
        return getLoginUser().getUsername();
    }
}
