package com.onlinejudge.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlinejudge.model.entity.question.JudgeCase;
import com.onlinejudge.model.entity.question.JudgeConfig;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 题目新增响应
 */
@Data
public class QuestionAddVO {
    /**
     * id
     */
    private Long id;

    /**
     * 题目名称
     */
    private String questionName;

    /**
     * 题目描述
     */
    private String questionContent;

    /**
     * 题解
     */
    private String questionAnswer;

    /**
     * 题目难度：easy/medium/hard
     */
    private Integer questionDifficulty;

    /**
     * 题目类型：队列/栈/树
     */
    private String questionType;

    /**
     * 测试用例
     */
    private String questionCases;

    /**
     * 题目配置
     */
    private String questionConfig;

    /**
     * 提交次数
     */
    private Integer submissionCount;

    /**
     * 通过次数
     */
    private Integer acceptedCount;

    /**
     * 创建用户id
     */
    private Integer createUser;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;



}