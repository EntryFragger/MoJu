package com.me.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.me.common.core.controller.BaseController;
import com.me.common.core.domain.R;
import com.me.system.domain.TActivityuser;
import com.me.system.service.ITActivityuserService;
import com.me.common.core.page.TableDataInfo;

/**
 * 加入得活动Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/system/activityuser")
public class TActivityuserController extends BaseController
{
    @Autowired
    private ITActivityuserService tActivityuserService;

    /**
     * 查询加入得活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TActivityuser tActivityuser)
    {
        startPage();
        List<TActivityuser> list = tActivityuserService.selectTActivityuserList(tActivityuser);
        return getDataTable(list);
    }

    /**
     * 获取加入得活动详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.success(tActivityuserService.selectTActivityuserById(id));
    }

    /**
     * 新增加入得活动
     */
    @PostMapping
    public R add(@RequestBody TActivityuser tActivityuser)
    {
        return toAjax(tActivityuserService.insertTActivityuser(tActivityuser));
    }

    /**
     * 修改加入得活动
     */
    @PutMapping
    public R edit(@RequestBody TActivityuser tActivityuser)
    {
        return toAjax(tActivityuserService.updateTActivityuser(tActivityuser));
    }

    /**
     * 删除加入得活动
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        return toAjax(tActivityuserService.deleteTActivityuserByIds(ids));
    }
}
