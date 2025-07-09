package com.onlinejudge.model.dto.question;

import lombok.Data;

import java.util.List;

/**
 * 题目分页请求
 */
@Data
public class QuestionPageDTO {

    /**
     * 当前页码
     */
    private Integer current = 1;

    /**
     * 每页数量
     */
    private Integer size = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = "ascend";

    /**
     * 题目名称
     */
    private String questionName;

    /**
     * 题目描述
     */
    private String questionContent;

    /**
     * 题目难度：easy/medium/hard
     */
    private Integer questionDifficulty;

    /**
     * 题目类型：队列/栈/树
     */
    private List<String> questionType;


}
