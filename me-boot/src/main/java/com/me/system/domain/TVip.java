package com.me.system.domain;

import com.me.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 充值优惠对象 t_vip
 *
 * @author me
 * @date 2022-06-09
 */
public class TVip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    private String name;

    /** 原价 */
    private BigDecimal bmoney;

    /** 现价 */
    private BigDecimal emoney;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setBmoney(BigDecimal bmoney)
    {
        this.bmoney = bmoney;
    }

    public BigDecimal getBmoney()
    {
        return bmoney;
    }
    public void setEmoney(BigDecimal emoney)
    {
        this.emoney = emoney;
    }

    public BigDecimal getEmoney()
    {
        return emoney;
    }

}
