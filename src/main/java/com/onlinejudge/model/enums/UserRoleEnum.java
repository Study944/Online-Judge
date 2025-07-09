package com.onlinejudge.model.enums;

import lombok.Getter;

/**
 * 用户角色枚举
 */
@Getter
public enum UserRoleEnum {
    USER("普通用户", "user"),
    ADMIN("管理员", "admin"),
    ;

    private final String name;
    private final String value;

    UserRoleEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static UserRoleEnum getEnumByValue(String value) {
        for (UserRoleEnum valueEnum : UserRoleEnum.values()) {
            if (valueEnum.value.equals(value)) {
                return valueEnum;
            }
        }
        return null;
    }
}
