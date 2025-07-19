package com.onlinejudge.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.common.UserContext;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.manager.judge.JudgeService;
import com.onlinejudge.model.dto.submission.SubmissionAddDTO;
import com.onlinejudge.model.dto.submission.SubmissionPageDTO;
import com.onlinejudge.model.entity.User;
import com.onlinejudge.model.entity.question.Question;
import com.onlinejudge.model.entity.submission.Submission;
import com.onlinejudge.model.enums.SubmissionLanguageEnum;
import com.onlinejudge.service.QuestionService;
import com.onlinejudge.service.SubmissionService;
import com.onlinejudge.mapper.SubmissionMapper;
import com.onlinejudge.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * 题目提交服务实现类
 */
@Service
public class SubmissionServiceImpl extends ServiceImpl<SubmissionMapper, Submission>
        implements SubmissionService {

    @Resource
    private QuestionService questionService;
    @Resource
    private UserService userService;
    @Resource
    @Lazy
    private JudgeService judgeService;

    @Override
    public Long  submit(SubmissionAddDTO submissionAddDTO) {
        // 1.获取提交参数
        Long questionId = submissionAddDTO.getQuestionId();
        String code = submissionAddDTO.getCode();
        Integer language = submissionAddDTO.getLanguage();
        // 2.校验提交参数
        ThrowUtil.throwIf(questionId == null, ErrorCode.PARAMS_ERROR, "题目id不能为空");
        ThrowUtil.throwIf(code == null, ErrorCode.PARAMS_ERROR, "代码不能为空");
        ThrowUtil.throwIf(language == null, ErrorCode.PARAMS_ERROR, "语言不能为空");
        Question question = questionService.getById(questionId);
        ThrowUtil.throwIf(question == null, ErrorCode.PARAMS_ERROR, "题目不存在");
        SubmissionLanguageEnum enumByValue = SubmissionLanguageEnum.getEnumByValue(language);
        ThrowUtil.throwIf(enumByValue == null, ErrorCode.PARAMS_ERROR, "不支持该语言");
        Long userId = UserContext.getUserId();
        User user = userService.getById(userId);
        // 3.保存提交
        Submission submission = new Submission();
        submission.setQuestionId(questionId);
        submission.setUserName(user.getUserName());
        submission.setCode(code);
        submission.setLanguage(language);
        // 4.返回提交
        boolean save = this.save(submission);
        ThrowUtil.throwIf(!save, ErrorCode.OPERATION_ERROR);
        Long submissionId = submission.getId();
        // 5.调用判题服务
        CompletableFuture.runAsync(()->{
            judgeService.doJudge(submissionId);
        });
        return submissionId;
    }

    @Override
    public Wrapper<Submission> getQueryWrapper(SubmissionPageDTO submissionQueryDTO) {
        // 1.获取查询信息
        String sortField = submissionQueryDTO.getSortField();
        String sortOrder = submissionQueryDTO.getSortOrder();
        Long questionId = submissionQueryDTO.getQuestionId();
        String userName = submissionQueryDTO.getUserName();
        Integer language = submissionQueryDTO.getLanguage();
        Integer state = submissionQueryDTO.getState();
        // 2.创建查询条件
        QueryWrapper<Submission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(questionId != null, "question_id", questionId);
        queryWrapper.eq(userName != null, "user_name", userName);
        queryWrapper.eq(language != null, "language", language);
        queryWrapper.eq(state != null, "state", state);
        queryWrapper.orderBy(sortField != null, sortOrder.equals("descend"), sortField);
        return queryWrapper;
    }
}




