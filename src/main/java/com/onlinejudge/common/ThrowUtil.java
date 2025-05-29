package com.onlinejudge.common;

import com.onlinejudge.exception.BusinessException;
import com.onlinejudge.exception.ErrorCode;

/**
 * 抛异常工具类
 */
public class ThrowUtil {

    public static void throwIf(boolean condition, ErrorCode errorCode) {
        if (condition) throw new BusinessException(errorCode);
    }

    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        if (condition) throw new BusinessException(errorCode.getCode(), message);
    }

}
