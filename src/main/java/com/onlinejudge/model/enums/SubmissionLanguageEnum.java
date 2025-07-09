package com.onlinejudge.model.enums;

import lombok.Getter;

@Getter
public enum SubmissionLanguageEnum {
    C("C", 0),
    CPP("C++", 1),
    JAVA("Java", 2),
    PYTHON("Python", 3),
    GO("Go", 5),
    ;

    private final String name;
    private final int value;

    SubmissionLanguageEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static SubmissionLanguageEnum getEnumByValue(int value) {
        for (SubmissionLanguageEnum valueEnum : SubmissionLanguageEnum.values()) {
            if (valueEnum.value == value) {
                return valueEnum;
            }
        }
        return null;
    }
}
