package com.onlinejudge.model.entity.submission;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 题目提交记录
 */
@TableName(value ="submission")
@Data
public class Submission {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 用户id
     */
    private String userName;

    /**
     * 提交代码
     */
    private String code;

    /**
     * 提交语言
     */
    private Integer language;

    /**
     * 题目状态：执行中/通过/超时/错误
     */
    private Integer state;

    /**
     * 判题信息：执行内存、执行时间
     */
    private String judgeInfo;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}