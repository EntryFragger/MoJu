package com.me.common.core.domain.model;

/**
 * 用户注册对象
 *
 * me.system
 */
public class RegisterBody extends LoginBody
{

    /** 用户邮箱 */
    private String email;

    /** 用户昵称 */
    private String nickName;
    /** 手机号码 */
    private String phonenumber;

    /**  证件号 */
    private String idCard;

    /** 家庭住址 */
    private String homeSite;

    /** 年龄 */
    private Long age;

    /** 所属班级id */
    private Long clzId;

    /** 所属班级名称 */
    private String clzName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHomeSite() {
        return homeSite;
    }

    public void setHomeSite(String homeSite) {
        this.homeSite = homeSite;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getClzId() {
        return clzId;
    }

    public void setClzId(Long clzId) {
        this.clzId = clzId;
    }

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }
}
