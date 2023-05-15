package com.me.system.domain;


import com.me.common.core.domain.BaseEntity;

/**
 * 省市区对象 t_region
 *
 * @author me
 * @date 2022-06-09
 */
public class TRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编码 */
    private String code;

    /** 名称 */
    private String name;

    /** 拼音 */
    private String pinyin;

    /** 前缀编码 */
    private String zipCode;

    /** 类型 */
    private String type;

    /** 前缀 */
    private String firstLetter;

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setPinyin(String pinyin)
    {
        this.pinyin = pinyin;
    }

    public String getPinyin()
    {
        return pinyin;
    }
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getZipCode()
    {
        return zipCode;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setFirstLetter(String firstLetter)
    {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter()
    {
        return firstLetter;
    }
}
