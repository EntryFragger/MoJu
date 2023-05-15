package com.me.system.domain;


import com.me.common.core.domain.BaseEntity;

/**
 * 加入得活动对象 t_activityuser
 *
 * @author me
 * @date 2022-06-09
 */
public class TActivityuser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    private Long userId;

    /** 用户名 */
    private String userName;

    /** 活动id */
    private Long activityId;

    /** 活动名称 */
    private String activityName;

    /** 主图 */
    private String url;

    /** 联系电话 */
    private String phone;

    /** 联系人 */
    private String receiver;

    /** 留言 */
    private String message;

    private String state;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setActivityId(Long activityId)
    {
        this.activityId = activityId;
    }

    public Long getActivityId()
    {
        return activityId;
    }
    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getActivityName()
    {
        return activityName;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getReceiver()
    {
        return receiver;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
