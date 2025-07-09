package com.onlinejudge.model.enums;

import lombok.Getter;

/**
 * 提交状态枚举
 */
@Getter
public enum SubmissionStateEnum {
    PENDING("等待处理", 0),
    COMPILING("正在编译", 1),
    RUNNING("正在运行", 2),
    ACCEPTED("通过", 3),
    WRONG_ANSWER("答案错误", 4),
    TIME_LIMIT_EXCEEDED("时间超限", 5),
    MEMORY_LIMIT_EXCEEDED("内存超限", 6),
    RUNTIME_ERROR("运行时错误", 7),
    SYNTAX_ERROR("语法错误", 8)
    ;

    private final String name;
    private final int value;

    SubmissionStateEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
