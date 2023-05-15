package com.me.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.me.system.mapper.TRegionMapper;
import com.me.system.domain.TRegion;
import com.me.system.service.ITRegionService;

/**
 * 省市区Service业务层处理
 *
 * @author me
 * @date 2022-06-09
 */
@Service
public class TRegionServiceImpl implements ITRegionService
{
    @Autowired
    private TRegionMapper tRegionMapper;

    /**
     * 查询省市区
     *
     * @param code 省市区主键
     * @return 省市区
     */
    @Override
    public TRegion selectTRegionByCode(String code)
    {
        return tRegionMapper.selectTRegionByCode(code);
    }

    /**
     * 查询省市区列表
     *
     * @param tRegion 省市区
     * @return 省市区
     */
    @Override
    public List<TRegion> selectTRegionList(TRegion tRegion)
    {
        return tRegionMapper.selectTRegionList(tRegion);
    }

    /**
     * 新增省市区
     *
     * @param tRegion 省市区
     * @return 结果
     */
    @Override
    public int insertTRegion(TRegion tRegion)
    {
        return tRegionMapper.insertTRegion(tRegion);
    }

    /**
     * 修改省市区
     *
     * @param tRegion 省市区
     * @return 结果
     */
    @Override
    public int updateTRegion(TRegion tRegion)
    {
        return tRegionMapper.updateTRegion(tRegion);
    }

    /**
     * 批量删除省市区
     *
     * @param codes 需要删除的省市区主键
     * @return 结果
     */
    @Override
    public int deleteTRegionByCodes(String[] codes)
    {
        return tRegionMapper.deleteTRegionByCodes(codes);
    }

    /**
     * 删除省市区信息
     *
     * @param code 省市区主键
     * @return 结果
     */
    @Override
    public int deleteTRegionByCode(String code)
    {
        return tRegionMapper.deleteTRegionByCode(code);
    }
}
