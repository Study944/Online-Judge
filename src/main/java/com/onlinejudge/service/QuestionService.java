package com.onlinejudge.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.onlinejudge.model.dto.question.QuestionAddDTO;
import com.onlinejudge.model.dto.question.QuestionPageDTO;
import com.onlinejudge.model.dto.question.QuestionUpdateDTO;
import com.onlinejudge.model.entity.question.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.onlinejudge.model.vo.QuestionAddVO;

/**
 * 题目服务实现
 */
public interface QuestionService extends IService<Question> {

    QuestionAddVO addQuestion(QuestionAddDTO questionAddDTO);

    Wrapper<Question> getQueryWrapper(QuestionPageDTO questionPageDTO);

    Question updateQuestion(QuestionUpdateDTO questionUpdateDTO);
}
