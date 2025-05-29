package com.onlinejudge.common;

import com.onlinejudge.exception.ErrorCode;

/**
 * 封装返回工具类
 */
public class ResultUtil {
    /**
     * 成功
     * @param data
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(20000,  "success", data);
    }

    /**
     * 异常
     * @param errorCode
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 异常
     * @param code
     * @param message
     */
    public static <T> BaseResponse<T> error(int code, String message) {
        return new BaseResponse<>(code,  message);
    }
}
