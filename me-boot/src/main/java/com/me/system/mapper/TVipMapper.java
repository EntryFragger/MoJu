package com.me.system.mapper;

import java.util.List;
import com.me.system.domain.TVip;

/**
 * 充值优惠Mapper接口
 * 
 * @author me
 * @date 2022-06-09
 */
public interface TVipMapper 
{
    /**
     * 查询充值优惠
     * 
     * @param id 充值优惠主键
     * @return 充值优惠
     */
    public TVip selectTVipById(Long id);

    /**
     * 查询充值优惠列表
     * 
     * @param tVip 充值优惠
     * @return 充值优惠集合
     */
    public List<TVip> selectTVipList(TVip tVip);

    /**
     * 新增充值优惠
     * 
     * @param tVip 充值优惠
     * @return 结果
     */
    public int insertTVip(TVip tVip);

    /**
     * 修改充值优惠
     * 
     * @param tVip 充值优惠
     * @return 结果
     */
    public int updateTVip(TVip tVip);

    /**
     * 删除充值优惠
     * 
     * @param id 充值优惠主键
     * @return 结果
     */
    public int deleteTVipById(Long id);

    /**
     * 批量删除充值优惠
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTVipByIds(Long[] ids);
}
