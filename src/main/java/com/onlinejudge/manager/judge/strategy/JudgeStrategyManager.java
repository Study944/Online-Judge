package com.onlinejudge.manager.judge.strategy;

import com.onlinejudge.model.enums.SubmissionLanguageEnum;
import com.onlinejudge.model.enums.SubmissionStateEnum;
import org.springframework.stereotype.Service;

/**
 * 策略模式工具类
 */
@Service
public class JudgeStrategyManager {

    public SubmissionStateEnum doJudge(JudgeContext judgeContext){
        Integer language = judgeContext.getLanguage();
        SubmissionLanguageEnum enumByValue = SubmissionLanguageEnum.getEnumByValue(language);
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategyImpl();
        if (enumByValue.getName()=="java"){
            judgeStrategy = new JavaJudgeStrategyImpl();
        }
        return judgeStrategy.doJudge(judgeContext);
    }

}
