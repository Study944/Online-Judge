package com.onlinejudge.model.dto.question;

import com.onlinejudge.model.entity.question.JudgeCase;
import com.onlinejudge.model.entity.question.JudgeConfig;
import lombok.Data;

import java.util.List;

/**
 * 题目更新请求
 */
@Data
public class QuestionUpdateDTO {

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
     * 题目答案
     */
    private String questionAnswer;

    /**
     * 题目难度：easy/medium/hard
     */
    private int questionDifficulty;

    /**
     * 题目类型：队列/栈/树
     */
    private List<String> questionType;

    /**
     * 测试用例
     */
    private List<JudgeCase> questionCases;

    /**
     * 示例用例
     */
    private List<JudgeCase> questionExample;

    /**
     * 题目配置
     */
    private JudgeConfig questionConfig;

}
