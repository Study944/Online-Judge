package com.onlinejudge.manager.judge.strategy;

import com.onlinejudge.model.entity.question.JudgeConfig;
import com.onlinejudge.model.entity.submission.JudgeInfo;
import com.onlinejudge.model.enums.SubmissionStateEnum;

import java.util.List;

/**
 * 默认判题策略
 */
public class DefaultJudgeStrategyImpl implements JudgeStrategy {
    @Override
    public SubmissionStateEnum doJudge(JudgeContext judgeContext) {
        // 1.获取上下文信息
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> outputList = judgeContext.getOutputList();
        List<String> outputResult = judgeContext.getOutputResult();
        JudgeConfig judgeConfig = judgeContext.getJudgeConfig();
        // 设置默认更新状态
        SubmissionStateEnum submissionState;
        // 3.1比对输出结果
        boolean outputEquals = outputList.equals(outputResult);
        if (!outputEquals) {
            // 答案错误
            submissionState = SubmissionStateEnum.WRONG_ANSWER;
        } else {
            // 答案正确
            submissionState = SubmissionStateEnum.ACCEPTED;
            // 3.2比对判题信息--内存和时间
            if (judgeInfo.getTime()>judgeConfig.getTimeLimits()){
                submissionState = SubmissionStateEnum.TIME_LIMIT_EXCEEDED;
            } else if (judgeInfo.getMemory()>judgeConfig.getMemoryLimits()){
                submissionState = SubmissionStateEnum.MEMORY_LIMIT_EXCEEDED;
            }
        }
        return submissionState;
    }
}
