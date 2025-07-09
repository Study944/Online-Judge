package com.onlinejudge.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.onlinejudge.model.dto.submission.SubmissionAddDTO;
import com.onlinejudge.model.dto.submission.SubmissionPageDTO;
import com.onlinejudge.model.entity.submission.Submission;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 题目提交服务接口
 */
public interface SubmissionService extends IService<Submission> {

    String submit(SubmissionAddDTO submissionAddDTO);

    Wrapper<Submission> getQueryWrapper(SubmissionPageDTO submissionQueryDTO);
}
