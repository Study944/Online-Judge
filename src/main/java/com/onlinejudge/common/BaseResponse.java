package com.onlinejudge.common;

import com.onlinejudge.exception.ErrorCode;
import lombok.Data;

/**
 * 通用返回类
 * @param <T> 返回数据类型
 */
@Data
public class BaseResponse<T> {
    // 状态码
    private int code;
    // 错误信息
    private String message;
    // 数据
    private T data;

    public BaseResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public BaseResponse(int code, String message) {
        this(code,message,null);
    }
    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }
    public BaseResponse(int code, T data) {
        this(code,  "", data);
    }
}
