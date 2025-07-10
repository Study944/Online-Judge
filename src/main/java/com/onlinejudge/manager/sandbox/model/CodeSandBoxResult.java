package com.onlinejudge.manager.sandbox.model;

import com.onlinejudge.model.entity.submission.JudgeInfo;
import lombok.Data;

import java.util.List;

/**
 * 代码沙箱响应结果
 */
@Data
public class CodeSandBoxResult {
    // 输出
    private List<String> output;
    // 错误信息
    private String message;
    // 结果状态
    private Integer status;
    // 判题信息
    private JudgeInfo judgeInfo;

}
