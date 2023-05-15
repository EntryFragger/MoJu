package com.me.system.api;

import com.me.common.core.controller.BaseController;
import com.me.common.core.domain.R;
import com.me.common.core.page.TableDataInfo;
import com.me.system.domain.TActivityuser;
import com.me.system.service.ITActivityuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 加入得活动Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/api/activityuser")
public class APITActivityuserController extends BaseController
{
    @Autowired
    private ITActivityuserService tActivityuserService;

    /**
     * 查询加入得活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TActivityuser tActivityuser)
    {
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
    @PostMapping("/add")
    public R add(@RequestBody TActivityuser tActivityuser)
    {
        return toAjax(tActivityuserService.insertTActivityuser(tActivityuser));
    }


    /**
     * 删除加入得活动
     */
    @PostMapping("/del/{id}")
    public R remove(@PathVariable Long id)
    {
        return toAjax(tActivityuserService.deleteTActivityuserById(id));
    }
}
