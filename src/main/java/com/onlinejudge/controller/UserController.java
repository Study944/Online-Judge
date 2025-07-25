package com.onlinejudge.controller;

import cn.hutool.core.bean.BeanUtil;
import com.onlinejudge.common.BaseResponse;
import com.onlinejudge.common.ResultUtil;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.common.UserContext;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.model.dto.user.UserLoginDTO;
import com.onlinejudge.model.dto.user.UserRegisterDTO;
import com.onlinejudge.model.entity.User;
import com.onlinejudge.model.vo.UserLoginVO;
import com.onlinejudge.model.vo.UserRegisterVO;
import com.onlinejudge.service.UserService;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterDTO 用户注册信息
     */
    @PostMapping("/register")
    public BaseResponse<UserRegisterVO> userRegister(@RequestBody UserRegisterDTO userRegisterDTO) {
        ThrowUtil.throwIf(userRegisterDTO == null, ErrorCode.PARAMS_ERROR, "参数为空");
        UserRegisterVO res = userService.userRegister(userRegisterDTO);
        return ResultUtil.success(res);
    }

    @PostMapping("/login")
    public BaseResponse<UserLoginVO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        ThrowUtil.throwIf(userLoginDTO == null, ErrorCode.PARAMS_ERROR, "参数为空");
        UserLoginVO res = userService.userLogin(userLoginDTO);
        return ResultUtil.success(res);
    }

    @GetMapping("/info")
    public BaseResponse<UserLoginVO> getUserInfo() {
        Long userId = UserContext.getUserId();
        User user = userService.getById(userId);
        UserLoginVO res = new UserLoginVO();
        BeanUtil.copyProperties(user, res);
        return ResultUtil.success(res);
    }

}
