package com.onlinejudge.model.dto.submission;

import lombok.Data;

/**
 * 题目提交请求
 */
@Data
public class SubmissionAddDTO {
    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 提交代码
     */
    private String code;

    /**
     * 提交语言
     */
    private Integer language;
}
