package com.me.system.api;

import com.me.common.core.controller.BaseController;
import com.me.common.core.domain.R;
import com.me.common.core.page.TableDataInfo;
import com.me.system.domain.TVip;
import com.me.system.service.ITVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 充值优惠Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/api/vip")
public class APITVipController extends BaseController
{
    @Autowired
    private ITVipService tVipService;

    /**
     * 查询充值优惠列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TVip tVip)
    {
        List<TVip> list = tVipService.selectTVipList(tVip);
        return getDataTable(list);
    }

    /**
     * 获取充值优惠详细信息
     */
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") Long id)
    {
        return R.success(tVipService.selectTVipById(id));
    }

}
