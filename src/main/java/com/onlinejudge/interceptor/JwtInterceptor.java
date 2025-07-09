package com.onlinejudge.interceptor;

import com.onlinejudge.common.JwtUtil;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.common.UserContext;
import com.onlinejudge.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT令牌拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {

    /**
     * 拦截请求校验是否登录，登录则设置用户信息
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1.检验令牌
        String token = request.getHeader("token");
        ThrowUtil.throwIf(token == null, ErrorCode.NOT_LOGIN_ERROR);
        JwtUtil.verify(token);
        // 2.保存用户id
        Long userId = JwtUtil.getUserId(token);
        UserContext.setUserId(userId);
        return true;
    }
}
