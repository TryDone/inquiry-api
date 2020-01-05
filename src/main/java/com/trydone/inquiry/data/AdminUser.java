package com.trydone.inquiry.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel("管理后台用户")
@Table(name = "inquiry_admin_user")
public class AdminUser {

    @ApiModelProperty("userId")
    @Id
    @Column(name = "user_id")
    private String userId;

    @ApiModelProperty("用户类型")
    @Column(name = "user_type")
    private String userType;

    @ApiModelProperty("用户名")
    @Column(name = "user_name")
    private String userName;

    @ApiModelProperty("密码")
    @Column(name = "password")
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
