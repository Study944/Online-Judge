package com.onlinejudge.model.dto.user;

import lombok.Data;

/**
 * 用户注册请求
 */
@Data
public class UserRegisterDTO {

    // 账号
    String account;
    // 密码
    String password;
    // 校验密码
    String checkPassword;

}
