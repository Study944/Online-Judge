package com.onlinejudge.aop;

import com.onlinejudge.annotation.AuthCheck;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.common.UserContext;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.model.entity.User;
import com.onlinejudge.model.enums.UserRoleEnum;
import com.onlinejudge.service.UserService;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 权限校验切面类
 */
@Aspect
@Component
public class AuthCheckAspect {

    @Resource
    private UserService userService;

    @Around("@annotation(authCheck)")
    private Object doAuthCheck(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String mustRole = authCheck.mustRole();
        User loginUser = userService.getById(UserContext.getUserId());
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByValue(loginUser.getUserRole());
        UserRoleEnum mustUserRoleEnum = UserRoleEnum.getEnumByValue(mustRole);
        // 1.mustRole=="admin"，校验当前登录用户是否为管理员
        ThrowUtil.throwIf(mustUserRoleEnum == UserRoleEnum.ADMIN && userRoleEnum != UserRoleEnum.ADMIN,
                ErrorCode.NO_AUTH_ERROR);
        // 2.mustRole=="user"，校验当前登录用户权限
        ThrowUtil.throwIf(mustUserRoleEnum == UserRoleEnum.USER && userRoleEnum == null,
                ErrorCode.NO_AUTH_ERROR);
        return joinPoint.proceed();
    }

}
