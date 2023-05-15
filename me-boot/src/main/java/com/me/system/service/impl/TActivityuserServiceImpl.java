package com.me.system.service.impl;

import java.util.List;
import com.me.common.utils.DateUtils;
import com.me.system.domain.TActivity;
import com.me.system.mapper.TActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.me.system.mapper.TActivityuserMapper;
import com.me.system.domain.TActivityuser;
import com.me.system.service.ITActivityuserService;

/**
 * 加入得活动Service业务层处理
 *
 * @author me
 * @date 2022-06-09
 */
@Service
public class TActivityuserServiceImpl implements ITActivityuserService
{
    @Autowired
    private TActivityuserMapper tActivityuserMapper;
    @Autowired
    private TActivityMapper activityMapper;

    /**
     * 查询加入得活动
     *
     * @param id 加入得活动主键
     * @return 加入得活动
     */
    @Override
    public TActivityuser selectTActivityuserById(Long id)
    {
        return tActivityuserMapper.selectTActivityuserById(id);
    }

    /**
     * 查询加入得活动列表
     *
     * @param tActivityuser 加入得活动
     * @return 加入得活动
     */
    @Override
    public List<TActivityuser> selectTActivityuserList(TActivityuser tActivityuser)
    {
        return tActivityuserMapper.selectTActivityuserList(tActivityuser);
    }

    /**
     * 新增加入得活动
     *
     * @param tActivityuser 加入得活动
     * @return 结果
     */
    @Override
    public int insertTActivityuser(TActivityuser tActivityuser)
    {
        TActivity activity = activityMapper.selectTActivityById(tActivityuser.getActivityId());
        activity.setNum(activity.getNum()+1);
        activityMapper.updateTActivity(activity);

        tActivityuser.setCreateTime(DateUtils.getNowDate());
        return tActivityuserMapper.insertTActivityuser(tActivityuser);
    }

    /**
     * 修改加入得活动
     *
     * @param tActivityuser 加入得活动
     * @return 结果
     */
    @Override
    public int updateTActivityuser(TActivityuser tActivityuser)
    {
        return tActivityuserMapper.updateTActivityuser(tActivityuser);
    }

    /**
     * 批量删除加入得活动
     *
     * @param ids 需要删除的加入得活动主键
     * @return 结果
     */
    @Override
    public int deleteTActivityuserByIds(Long[] ids)
    {
        return tActivityuserMapper.deleteTActivityuserByIds(ids);
    }

    /**
     * 删除加入得活动信息
     *
     * @param id 加入得活动主键
     * @return 结果
     */
    @Override
    public int deleteTActivityuserById(Long id)
    {
        TActivityuser  tActivityuser = tActivityuserMapper.selectTActivityuserById(id);
        TActivity activity = activityMapper.selectTActivityById(tActivityuser.getActivityId());
        activity.setNum(activity.getNum()-1);
        activityMapper.updateTActivity(activity);
        return tActivityuserMapper.deleteTActivityuserById(id);
    }
}
