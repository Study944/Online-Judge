package com.onlinejudge.model.entity.submission;

import lombok.Data;

/**
 * 判题结果信息
 */
@Data
public class JudgeInfo {

    // 程序占用内存
    private Long memory;
    // 程序运行时间
    private Long time;

}
