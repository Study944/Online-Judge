package com.onlinejudge.manager.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 代码沙箱判题提交请求
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodeSandBoxDTO {

    // 提交代码
    String code;
    // 输入
    List<String> input;
    // 语言
    Integer language;

}
