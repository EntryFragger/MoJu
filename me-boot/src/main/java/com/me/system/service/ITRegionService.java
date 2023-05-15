package com.me.system.service;

import java.util.List;
import com.me.system.domain.TRegion;

/**
 * 省市区Service接口
 * 
 * @author me
 * @date 2022-06-09
 */
public interface ITRegionService 
{
    /**
     * 查询省市区
     * 
     * @param code 省市区主键
     * @return 省市区
     */
    public TRegion selectTRegionByCode(String code);

    /**
     * 查询省市区列表
     * 
     * @param tRegion 省市区
     * @return 省市区集合
     */
    public List<TRegion> selectTRegionList(TRegion tRegion);

    /**
     * 新增省市区
     * 
     * @param tRegion 省市区
     * @return 结果
     */
    public int insertTRegion(TRegion tRegion);

    /**
     * 修改省市区
     * 
     * @param tRegion 省市区
     * @return 结果
     */
    public int updateTRegion(TRegion tRegion);

    /**
     * 批量删除省市区
     * 
     * @param codes 需要删除的省市区主键集合
     * @return 结果
     */
    public int deleteTRegionByCodes(String[] codes);

    /**
     * 删除省市区信息
     * 
     * @param code 省市区主键
     * @return 结果
     */
    public int deleteTRegionByCode(String code);
}
