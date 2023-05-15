package com.me.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.me.system.mapper.TVipMapper;
import com.me.system.domain.TVip;
import com.me.system.service.ITVipService;

/**
 * 充值优惠Service业务层处理
 *
 * @author me
 * @date 2022-06-09
 */
@Service
public class TVipServiceImpl implements ITVipService
{
    @Autowired
    private TVipMapper tVipMapper;

    /**
     * 查询充值优惠
     *
     * @param id 充值优惠主键
     * @return 充值优惠
     */
    @Override
    public TVip selectTVipById(Long id)
    {
        return tVipMapper.selectTVipById(id);
    }

    /**
     * 查询充值优惠列表
     *
     * @param tVip 充值优惠
     * @return 充值优惠
     */
    @Override
    public List<TVip> selectTVipList(TVip tVip)
    {
        return tVipMapper.selectTVipList(tVip);
    }

    /**
     * 新增充值优惠
     *
     * @param tVip 充值优惠
     * @return 结果
     */
    @Override
    public int insertTVip(TVip tVip)
    {
        return tVipMapper.insertTVip(tVip);
    }

    /**
     * 修改充值优惠
     *
     * @param tVip 充值优惠
     * @return 结果
     */
    @Override
    public int updateTVip(TVip tVip)
    {
        return tVipMapper.updateTVip(tVip);
    }

    /**
     * 批量删除充值优惠
     *
     * @param ids 需要删除的充值优惠主键
     * @return 结果
     */
    @Override
    public int deleteTVipByIds(Long[] ids)
    {
        return tVipMapper.deleteTVipByIds(ids);
    }

    /**
     * 删除充值优惠信息
     *
     * @param id 充值优惠主键
     * @return 结果
     */
    @Override
    public int deleteTVipById(Long id)
    {
        return tVipMapper.deleteTVipById(id);
    }
}
