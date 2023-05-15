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
import com.me.system.domain.TVip;
import com.me.system.service.ITVipService;
import com.me.common.core.page.TableDataInfo;

/**
 * 充值优惠Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/system/vip")
public class TVipController extends BaseController
{
    @Autowired
    private ITVipService tVipService;

    /**
     * 查询充值优惠列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TVip tVip)
    {
        startPage();
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

    /**
     * 新增充值优惠
     */
    @PostMapping
    public R add(@RequestBody TVip tVip)
    {
        return toAjax(tVipService.insertTVip(tVip));
    }

    /**
     * 修改充值优惠
     */
    @PutMapping
    public R edit(@RequestBody TVip tVip)
    {
        return toAjax(tVipService.updateTVip(tVip));
    }

    /**
     * 删除充值优惠
     */
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable Long[] ids)
    {
        return toAjax(tVipService.deleteTVipByIds(ids));
    }
}
