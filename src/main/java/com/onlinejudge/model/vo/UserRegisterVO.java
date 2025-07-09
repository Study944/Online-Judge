package com.onlinejudge.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户注册响应
 */
@Data
public class UserRegisterVO implements Serializable {

    /**
     * id
     */
    Long id;

    /**
     * 账号
     */
    String userAccount;

    /**
     * 密码
     */
    String userPassword;

    /**
     * 用户昵称
     */
    String userName;

    /**
     * 用户简介
     */
    String userProfile;

    /**
     * 用户角色：user/admin/ban
     */
    String userRole;

    /**
     * 创建时间
     */
    Date createTime;


}
