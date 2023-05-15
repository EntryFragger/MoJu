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
import com.me.system.domain.TRegion;
import com.me.system.service.ITRegionService;
import com.me.common.core.page.TableDataInfo;

/**
 * 省市区Controller
 *
 * @author me
 * @date 2022-06-09
 */
@RestController
@RequestMapping("/system/region")
public class TRegionController extends BaseController
{
    @Autowired
    private ITRegionService tRegionService;

    /**
     * 查询省市区列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:list')")
    @GetMapping("/list")
    public TableDataInfo list(TRegion tRegion)
    {
        startPage();
        List<TRegion> list = tRegionService.selectTRegionList(tRegion);
        return getDataTable(list);
    }

    /**
     * 获取省市区详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:query')")
    @GetMapping(value = "/{code}")
    public R getInfo(@PathVariable("code") String code)
    {
        return R.success(tRegionService.selectTRegionByCode(code));
    }

    /**
     * 新增省市区
     */
    @PreAuthorize("@ss.hasPermi('system:region:add')")
    @PostMapping
    public R add(@RequestBody TRegion tRegion)
    {
        return toAjax(tRegionService.insertTRegion(tRegion));
    }

    /**
     * 修改省市区
     */
    @PreAuthorize("@ss.hasPermi('system:region:edit')")
    @PutMapping
    public R edit(@RequestBody TRegion tRegion)
    {
        return toAjax(tRegionService.updateTRegion(tRegion));
    }

    /**
     * 删除省市区
     */
    @PreAuthorize("@ss.hasPermi('system:region:remove')")
    @DeleteMapping("/{codes}")
    public R remove(@PathVariable String[] codes)
    {
        return toAjax(tRegionService.deleteTRegionByCodes(codes));
    }
}
