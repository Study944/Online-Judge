package com.onlinejudge.manager.judge.strategy;

import com.onlinejudge.model.enums.SubmissionStateEnum;

/**
 * 判题策略模式
 */
public interface JudgeStrategy {

    SubmissionStateEnum doJudge(JudgeContext judgeContext);

}
