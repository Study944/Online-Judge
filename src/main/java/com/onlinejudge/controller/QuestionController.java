package com.onlinejudge.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onlinejudge.annotation.AuthCheck;
import com.onlinejudge.common.BaseResponse;
import com.onlinejudge.common.ResultUtil;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.model.dto.question.QuestionAddDTO;
import com.onlinejudge.model.dto.question.QuestionPageDTO;
import com.onlinejudge.model.dto.question.QuestionUpdateDTO;
import com.onlinejudge.model.entity.question.Question;
import com.onlinejudge.model.vo.QuestionAddVO;
import com.onlinejudge.model.vo.QuestionVO;
import com.onlinejudge.service.QuestionService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    /**
     * 添加题目
     */
    @PostMapping("/addQuestion")
    @AuthCheck(mustRole =  "admin")
    public BaseResponse<QuestionAddVO> addQuestion(@RequestBody QuestionAddDTO questionAddDTO) {
        ThrowUtil.throwIf(questionAddDTO == null, ErrorCode.PARAMS_ERROR);
        QuestionAddVO res = questionService.addQuestion(questionAddDTO);
        return ResultUtil.success(res);
    }

    /**
     * 根据ID查询题目
     *
     * @param questionId
     */
    @GetMapping("/getById")
    public BaseResponse<QuestionVO> getQuestionById(Long questionId) {
        ThrowUtil.throwIf(questionId == null, ErrorCode.PARAMS_ERROR);
        Question question = questionService.getById(questionId);
        return ResultUtil.success(BeanUtil.copyProperties(question, QuestionVO.class));
    }

    /**
     * 获取题目分页
     */
    @PostMapping("/listPage")
    public BaseResponse<Page<QuestionVO>> listQuestion(@RequestBody QuestionPageDTO questionPageDTO) {
        Integer current = questionPageDTO.getCurrent();
        Integer size = questionPageDTO.getSize();
        Page<Question> page = questionService.page(new Page<>(current, size),
                questionService.getQueryWrapper(questionPageDTO));
        Page<QuestionVO> questionVOPage = new Page<>(current, size, page.getTotal());
        // 类型转换
        List<QuestionVO> questionVOList = page.getRecords().stream()
                .map(question -> BeanUtil.copyProperties(question, QuestionVO.class))
                .toList();
        questionVOPage.setRecords(questionVOList);
        return ResultUtil.success(questionVOPage);
    }

    /**
     * 获取题目列表
     */
    @PostMapping("/list")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Page<Question>> list(@RequestBody QuestionPageDTO questionPageDTO) {
        Integer current = questionPageDTO.getCurrent();
        Integer size = questionPageDTO.getSize();
        Page<Question> page = questionService.page(new Page<>(current, size),
                questionService.getQueryWrapper(questionPageDTO));
        return ResultUtil.success(page);
    }

    /**
     * 删除题目
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> deleteQuestion(@RequestBody Long questionId) {
        ThrowUtil.throwIf(questionId == null, ErrorCode.PARAMS_ERROR);
        return ResultUtil.success(questionService.removeById(questionId));
    }

    /**
     * 更新题目
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Question> updateQuestion(@RequestBody QuestionUpdateDTO questionUpdateDTO) {
        ThrowUtil.throwIf(questionUpdateDTO == null, ErrorCode.PARAMS_ERROR);
        Question update = questionService.updateQuestion(questionUpdateDTO);
        return ResultUtil.success(update);
    }

}
