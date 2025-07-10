package com.onlinejudge.manager.judge.strategy;

import com.onlinejudge.model.entity.question.JudgeConfig;
import com.onlinejudge.model.entity.submission.JudgeInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 判题策略上下文
 */
@Data
@Builder
public class JudgeContext {

    private Long submissionId;

    private JudgeInfo judgeInfo;

    private List<String> outputList;

    private List<String> outputResult;

    private JudgeConfig judgeConfig;

    private Integer language;

}
