package com.onlinejudge.model.entity.question;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

/**
 * 题目
 */
@TableName(value ="question")
@Data
public class Question {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
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
     * 题目难度：easy-1/medium-2/hard-3
     */
    private Integer questionDifficulty;

    /**
     * 题目类型：队列/栈/树
     */
    private String questionType;

    /**
     * 判题数据
     */
    private String questionCases;

    /**
     * 示例
     */
    private String questionExample;

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
     * 编辑时间
     */
    private Date editTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;
}