package com.onlinejudge.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 题目响应
 */
@Data
public class QuestionVO {
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
     * 题目难度：easy/medium/hard
     */
    private Integer questionDifficulty;

    /**
     * 测试用例
     */
    private String questionCases;

    /**
     * 题目类型：队列/栈/树
     */
    private String questionType;

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