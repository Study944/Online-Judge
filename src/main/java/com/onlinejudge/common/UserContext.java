package com.onlinejudge.common;

/**
 * 用户上下文
 */
public class UserContext {
    // 使用ThreadLocal线程独有变量保存当前线程的登录用户ID
    private static final ThreadLocal<Long> userThreadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的登录用户ID
     * @param userId
     */
    public static void setUserId(Long userId) {
        userThreadLocal.set(userId);
    }

    /**
     * 获取当前线程的登录用户ID
     */
    public static Long getUserId() {
        return userThreadLocal.get();
    }

    /**
     * 删除当前线程的登录用户ID
     */
    public static void removeUserId() {
        userThreadLocal.remove();
    }

}
