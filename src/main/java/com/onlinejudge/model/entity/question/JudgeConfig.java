package com.onlinejudge.model.entity.question;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题配置
 */
@Data
public class JudgeConfig implements Serializable {
    // 内存限制
    Long MemoryLimits;
    // 时间限制
    Long TimeLimits;
}
