package com.onlinejudge.model.enums;

import lombok.Getter;

/**
 * 提交状态枚举
 */
@Getter
public enum SubmissionStateEnum {
    PENDING("等待处理", 0),
    ACCEPTED("通过", 1),
    WRONG_ANSWER("答案错误", 2),
    TIME_LIMIT_EXCEEDED("时间超限", 3),
    MEMORY_LIMIT_EXCEEDED("内存超限", 4),
    COMPILE_ERROR("编译错误", 5),
    RUNTIME_ERROR("运行错误", 6),
    ;

    private final String name;
    private final int value;

    SubmissionStateEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public static SubmissionStateEnum getByValue(Integer value) {
        for (SubmissionStateEnum valueEnum : values()) {
            if (valueEnum.value == value) {
                return valueEnum;
            }
        }
        return null;
    }
}
