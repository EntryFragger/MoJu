package com.me.system.domain;


import com.me.common.core.domain.BaseEntity;

/**
 * 活动对象 t_activity
 *
 * @author me
 * @date 2022-06-09
 */
public class TActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 图片 */
    private String url;

    /** 标题 */
    private String title;

    /** 分类名称 */
    private String typeName;

    /** 正常,解散,关闭 */
    private String state;

    /** 计划参与人数 */
    private Long pnum;

    /** 实际参与人数 */
    private Long num;

    /** 省code */
    private String provinceCode;

    /** 省名称 */
    private String provinceName;

    /** 市区code */
    private String cityCode;

    /** 市区名称 */
    private String cityName;

    /** 区域code */
    private String areaCode;

    /** 区域名称 */
    private String areaName;


    /**
     * 小程序区域搜索值
     */
    private String[] areaVal;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPnum(Long pnum)
    {
        this.pnum = pnum;
    }

    public Long getPnum()
    {
        return pnum;
    }
    public void setNum(Long num)
    {
        this.num = num;
    }

    public Long getNum()
    {
        return num;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String[] getAreaVal() {
        return areaVal;
    }

    public void setAreaVal(String[] areaVal) {
        this.areaVal = areaVal;
    }
}
