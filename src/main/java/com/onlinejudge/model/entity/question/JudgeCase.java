package com.onlinejudge.model.entity.question;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class JudgeCase implements Serializable {
    // 输入
    String input;
    // 输出
    String output;

}
