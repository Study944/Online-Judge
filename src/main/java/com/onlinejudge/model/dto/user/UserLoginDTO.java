package com.onlinejudge.model.dto.user;

import lombok.Data;

/**
 * 用户注册请求
 */
@Data
public class UserLoginDTO {

    // 账号
    String account;
    // 密码
    String password;

}
