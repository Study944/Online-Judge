package com.onlinejudge.service;

import com.onlinejudge.model.dto.user.UserLoginDTO;
import com.onlinejudge.model.dto.user.UserRegisterDTO;
import com.onlinejudge.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.onlinejudge.model.vo.UserLoginVO;
import com.onlinejudge.model.vo.UserRegisterVO;

/**
 *  用户服务
 */
public interface UserService extends IService<User> {

    UserRegisterVO userRegister(UserRegisterDTO userRegisterDTO);

    UserLoginVO userLogin(UserLoginDTO userLoginDTO);
}
