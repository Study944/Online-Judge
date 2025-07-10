package com.onlinejudge.manager.judge.strategy;

import com.onlinejudge.model.entity.question.JudgeConfig;
import com.onlinejudge.model.entity.submission.JudgeInfo;
import com.onlinejudge.model.enums.SubmissionStateEnum;

import java.util.List;

/**
 * 默认判题策略
 */
public class JavaJudgeStrategyImpl implements JudgeStrategy {
    @Override
    public SubmissionStateEnum doJudge(JudgeContext judgeContext) {
        // 1.获取上下文信息
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> outputList = judgeContext.getOutputList();
        List<String> outputResult = judgeContext.getOutputResult();
        JudgeConfig judgeConfig = judgeContext.getJudgeConfig();
        // 2.构建更新数据库信息，已经确定更新内容为使用时间和内存，需要确定状态
        // 设置默认更新状态
        SubmissionStateEnum submissionState = SubmissionStateEnum.RUNTIME_ERROR;
        // 3.1比对输出结果
        boolean outputEquals = outputList.equals(outputResult);
        if (!outputEquals) {
            // 答案错误
            submissionState = SubmissionStateEnum.WRONG_ANSWER;
        } else {
            // 答案正确
            submissionState = SubmissionStateEnum.ACCEPTED;
            // 3.2比对判题信息--内存和时间
            // Java语言的时间限制更加宽泛，假定时间限制多100ms
            if (judgeInfo.getTime()+100L>judgeConfig.getTimeLimits()){
                submissionState = SubmissionStateEnum.TIME_LIMIT_EXCEEDED;
            } else if (judgeInfo.getMemory()>judgeConfig.getMemoryLimits()){
                submissionState = SubmissionStateEnum.MEMORY_LIMIT_EXCEEDED;
            }
        }
        return submissionState;
    }
}
