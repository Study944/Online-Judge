package com.onlinejudge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onlinejudge.common.JwtUtil;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.model.dto.user.UserLoginDTO;
import com.onlinejudge.model.dto.user.UserRegisterDTO;
import com.onlinejudge.model.entity.User;
import com.onlinejudge.model.vo.UserLoginVO;
import com.onlinejudge.model.vo.UserRegisterVO;
import com.onlinejudge.service.UserService;
import com.onlinejudge.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    /**
     * 用户注册
     * @param userRegisterDTO 注册参数
     * @return 注册结果
     */
    @Override
    public UserRegisterVO userRegister(UserRegisterDTO userRegisterDTO) {
        // 1.注册参数校验
        String account = userRegisterDTO.getAccount();
        String password = userRegisterDTO.getPassword();
        String checkPassword = userRegisterDTO.getCheckPassword();
        ThrowUtil.throwIf(account.length() < 5, ErrorCode.PARAMS_ERROR, "账号过短");
        ThrowUtil.throwIf(password.length() < 8, ErrorCode.PARAMS_ERROR, "密码过短");
        ThrowUtil.throwIf(!password.equals(checkPassword), ErrorCode.PARAMS_ERROR, "密码不一致");
        // 2.账户不能重复
        boolean exists = this.lambdaQuery()
                .eq(User::getUserAccount, account)
                .exists();
        ThrowUtil.throwIf(exists, ErrorCode.OPERATION_ERROR,"账号重复");
        // 3.密码加密
        String encryptPassword = getEncryptPassword(password);
        // 4.插入数据
        User user = new User();
        user.setUserAccount(account);
        user.setUserPassword(encryptPassword);
        boolean save = this.save(user);
        // 5.返回脱敏结果
        ThrowUtil.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return BeanUtil.copyProperties(user, UserRegisterVO.class);
    }

    @Override
    public UserLoginVO userLogin(UserLoginDTO userLoginDTO) {
        // 1.参数校验
        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();
        ThrowUtil.throwIf(account.length() < 5, ErrorCode.PARAMS_ERROR, "账号过短");
        ThrowUtil.throwIf(password.length() < 8, ErrorCode.PARAMS_ERROR, "密码过短");
        // 2.查询数据库
        User user = this.lambdaQuery()
                .eq(User::getUserAccount, account)
                .one();
        ThrowUtil.throwIf(user == null, ErrorCode.OPERATION_ERROR, "用户不存在");
        // 3.密码匹配
        String encryptPassword = getEncryptPassword(password);
        ThrowUtil.throwIf(!user.getUserPassword().equals(encryptPassword),
                ErrorCode.OPERATION_ERROR, "密码错误");
        // 4.返回令牌
        UserLoginVO userLoginVO = BeanUtil.copyProperties(user, UserLoginVO.class);
        String token = JwtUtil.generateToken(user.getId());
        userLoginVO.setToken(token);
        return userLoginVO;
    }


    /**
     * 加密
     * @param password 原始密码
     */
    public String getEncryptPassword(String password) {
        String salt = "zxc";
        return DigestUtils.md5DigestAsHex((salt + password).getBytes());
    }
}




