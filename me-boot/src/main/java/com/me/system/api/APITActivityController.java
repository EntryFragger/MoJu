package com.me.system.api;

import com.me.common.core.controller.BaseController;
import com.me.common.core.domain.R;
import com.me.common.core.page.TableDataInfo;
import com.me.common.utils.StringUtils;
import com.me.system.domain.TActivity;
import com.me.system.service.ITActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/api/activity")
public class APITActivityController extends BaseController
{
    @Autowired
    private ITActivityService tActivityService;

    /**
     * 查询活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TActivity tActivity)
    {
        if(tActivity.getAreaVal().length > 0){
            String[] arrays = tActivity.getAreaVal();
            tActivity.setProvinceName(arrays[0]);
            tActivity.setCityName(arrays[1]);
            tActivity.setAreaName(arrays[2]);
        }

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


}
