package com.onlinejudge.manager.judge;

import cn.hutool.json.JSONUtil;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.manager.judge.strategy.JudgeContext;
import com.onlinejudge.manager.judge.strategy.JudgeStrategyManager;
import com.onlinejudge.manager.sandbox.SandBoxFactory;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;
import com.onlinejudge.model.entity.question.JudgeCase;
import com.onlinejudge.model.entity.question.JudgeConfig;
import com.onlinejudge.model.entity.question.Question;
import com.onlinejudge.model.entity.submission.JudgeInfo;
import com.onlinejudge.model.entity.submission.Submission;
import com.onlinejudge.model.enums.SubmissionStateEnum;
import com.onlinejudge.service.QuestionService;
import com.onlinejudge.service.SubmissionService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${sandbox.type}")
    private String sandboxType;

    @Resource
    private SubmissionService submissionService;
    @Resource
    private QuestionService questionService;
    @Resource
    private JudgeStrategyManager judgeStrategyManager;


    @Override
    public String doJudge(Long submissionId) {
        // 1.获取提交信息
        Submission submission = submissionService.getById(submissionId);
        Long questionId = submission.getQuestionId();
        String code = submission.getCode();
        Integer language = submission.getLanguage();
        Question question = questionService.getById(questionId);
        String questionCases = question.getQuestionCases();
        // 1.1获取测试用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(questionCases, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).toList();
        List<String> outputList = judgeCaseList.stream().map(JudgeCase::getOutput).toList();
        // 2.调用沙箱获取程序输出结果
        CodeSandBox sandBox = SandBoxFactory.getSandBox(sandboxType);
        CodeSandBoxDTO codeSandBoxDTO = CodeSandBoxDTO.builder()
                .code(code)
                .language(language)
                .input(inputList)
                .build();
        CodeSandBoxResult codeSandBoxResult = sandBox.runCode(codeSandBoxDTO);
        // 3.结果比对
        List<String> outputResult = codeSandBoxResult.getOutput();
        JudgeInfo judgeInfo = codeSandBoxResult.getJudgeInfo();
        Submission submissionUpdate = new Submission();
        submissionUpdate.setId(submissionId);
        submissionUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        SubmissionStateEnum submissionState = SubmissionStateEnum.RUNTIME_ERROR;
        submissionUpdate.setState(submissionState.getValue());
        // 构建策略模式上下文
        JudgeContext judgeContext = JudgeContext.builder()
                .submissionId(submissionId)
                .judgeInfo(judgeInfo)
                .outputList(outputList)
                .outputResult(outputResult)
                .judgeConfig(JSONUtil.toBean(question.getQuestionConfig(), JudgeConfig.class))
                .language(language)
                .build();
        // 4.策略模式获取判题结果
        SubmissionStateEnum submissionStateEnum = judgeStrategyManager.doJudge(judgeContext);
        if (submissionStateEnum != null){
            submissionUpdate.setState(submissionStateEnum.getValue());
        }
        boolean update = submissionService.updateById(submissionUpdate);
        ThrowUtil.throwIf(!update, ErrorCode.OPERATION_ERROR);
        return "判题结束";
    }
}
