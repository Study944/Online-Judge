package com.onlinejudge.model.enums;

public enum QuestionDifficultyEnum {
    EASY("简单", 1),
    MEDIUM("中等", 2),
    HARD("困难", 3);
    ;

    private final String name;
    private final int value;

    QuestionDifficultyEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static QuestionDifficultyEnum getByValue(int value) {
        for (QuestionDifficultyEnum questionDifficultyEnum : QuestionDifficultyEnum.values()) {
            if (questionDifficultyEnum.value == value) {
                return questionDifficultyEnum;
            }
        }
        return null;
    }
}
