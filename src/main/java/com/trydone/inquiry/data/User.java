package com.trydone.inquiry.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
@ApiModel("用户")
@Table(name = "inquiry_user")
public class User {

    /**
     * 主键
     */
    @ApiModelProperty("id")
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @Column(name = "name")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    @Column(name = "sex")
    private String sex;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    @Column(name = "birthday")
    private String birthday;
    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    @Column(name = "tel_no")
    private String telNo;

    /**
     * 绑定关系(父亲、母亲等)
     */
    @ApiModelProperty("绑定关系(父亲、母亲等)")
    @Column(name = "relation_type")
    private String relationType;

    /**
     * 绑定ID
     */
    @ApiModelProperty("绑定ID")
    @Column(name = "relation_id")
    private String relationId;

    /**
     * 关联用户标识
     */
    @ApiModelProperty("关联用户标识")
    @Column(name = "open_id")
    private String openId;

    /**
     * 既往史
     */
    @ApiModelProperty("既往史")
    @Column(name = "past_history")
    private String pastHistory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }
}
