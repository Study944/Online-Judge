package com.onlinejudge.model.dto.submission;

import lombok.Data;

/**
 * 记录查询请求
 */
@Data
public class SubmissionPageDTO {

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
    private String sortField = "id";

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = "ascend";

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 提交语言
     */
    private Integer language;

    /**
     * 题目状态：执行中/通过/超时/错误
     */
    private Integer state;

}
