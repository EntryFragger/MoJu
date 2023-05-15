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
import com.me.system.domain.TActivity;
import com.me.system.service.ITActivityService;
import com.me.common.core.page.TableDataInfo;

/**
 * 活动Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/system/activity")
public class TActivityController extends BaseController
{
    @Autowired
    private ITActivityService tActivityService;

    /**
     * 查询活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TActivity tActivity)
    {
        startPage();
        List<TActivity> list = tActivityService.selectTActivityList(tActivity);
        return getDataTable(list);
    }


    /**
     * 获取活动详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.success(tActivityService.selectTActivityById(id));
    }

    /**
     * 新增活动
     */
    @PostMapping
    public R add(@RequestBody TActivity tActivity)
    {
        return toAjax(tActivityService.insertTActivity(tActivity));
    }

    /**
     * 修改活动
     */
    @PutMapping
    public R edit(@RequestBody TActivity tActivity)
    {
        return toAjax(tActivityService.updateTActivity(tActivity));
    }

    /**
     * 删除活动
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        return toAjax(tActivityService.deleteTActivityByIds(ids));
    }
}
