package com.onlinejudge.exception;

import com.onlinejudge.common.BaseResponse;
import com.onlinejudge.common.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 业务异常
     * @param e
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?>businessExceptionHandler(BusinessException e) {
        log.error("BusinessException:", e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    /**
     * 系统异常
     * @param e
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse<?> exceptionHandler(Exception e) {
        log.error("SystemException:", e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }

}
