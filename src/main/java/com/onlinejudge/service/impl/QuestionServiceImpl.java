package com.onlinejudge.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.common.UserContext;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.model.dto.question.QuestionAddDTO;
import com.onlinejudge.model.dto.question.QuestionPageDTO;
import com.onlinejudge.model.dto.question.QuestionUpdateDTO;
import com.onlinejudge.model.entity.question.JudgeCase;
import com.onlinejudge.model.entity.question.JudgeConfig;
import com.onlinejudge.model.entity.question.Question;
import com.onlinejudge.model.enums.QuestionDifficultyEnum;
import com.onlinejudge.model.vo.QuestionAddVO;
import com.onlinejudge.service.QuestionService;
import com.onlinejudge.mapper.QuestionMapper;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 题目服务实现类
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

    /**
     * 题目添加实现
     * @param questionAddDTO
     */
    @Override
    public QuestionAddVO addQuestion(QuestionAddDTO questionAddDTO) {
        // 1.参数校验
        String questionName = questionAddDTO.getQuestionName();
        String questionContent = questionAddDTO.getQuestionContent();
        String questionAnswer = questionAddDTO.getQuestionAnswer();
        int questionDifficulty = questionAddDTO.getQuestionDifficulty();
        List<String> questionType = questionAddDTO.getQuestionType();
        List<JudgeCase> questionCases = questionAddDTO.getQuestionCases();
        List<JudgeCase> questionExample = questionAddDTO.getQuestionExample();
        JudgeConfig questionConfig = questionAddDTO.getQuestionConfig();
        QuestionDifficultyEnum questionDifficultyEnum = QuestionDifficultyEnum.getByValue(questionDifficulty);
        ThrowUtil.throwIf(questionDifficultyEnum == null, ErrorCode.PARAMS_ERROR, "题目难度错误");
        ThrowUtil.throwIf(questionName == null || questionName.length() < 1, ErrorCode.PARAMS_ERROR, "题目名称不能为空");
        ThrowUtil.throwIf(questionContent == null || questionContent.length() < 1, ErrorCode.PARAMS_ERROR, "题目内容不能为空");
        ThrowUtil.throwIf(questionCases == null , ErrorCode.PARAMS_ERROR, "题目用例不能为空");
        ThrowUtil.throwIf(questionConfig == null , ErrorCode.PARAMS_ERROR, "题目配置不能为空");
        // 2.新增题目
        Question question = new Question();
        question.setQuestionName(questionName);
        question.setQuestionContent(questionContent);
        question.setQuestionAnswer(questionAnswer);
        question.setQuestionDifficulty(questionDifficulty);
        question.setQuestionType(CollUtil.join(questionType, ","));
        question.setQuestionCases(JSONUtil.toJsonStr(questionCases));
        question.setQuestionExample(JSONUtil.toJsonStr(questionExample));
        question.setQuestionConfig(JSONUtil.toJsonStr(questionConfig));
        Long userId = UserContext.getUserId();
        if (userId != null) question.setCreateUser(Math.toIntExact(userId));
        // 3.插入数据
        boolean save = this.save(question);
        ThrowUtil.throwIf(!save, ErrorCode.OPERATION_ERROR);
        return BeanUtil.copyProperties(question, QuestionAddVO.class);
    }

    /**
     * 获取查询条件
     * @param questionPageDTO
     */
    @Override
    public Wrapper<Question> getQueryWrapper(QuestionPageDTO questionPageDTO) {
        // 1.获取查询条件
        String sortField = questionPageDTO.getSortField();
        String sortOrder = questionPageDTO.getSortOrder();
        String questionName = questionPageDTO.getQuestionName();
        String questionContent = questionPageDTO.getQuestionContent();
        Integer questionDifficulty = questionPageDTO.getQuestionDifficulty();
        List<String> questionType = questionPageDTO.getQuestionType();
        // 2.创建查询条件
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(questionName!= null, "question_name", questionName);
        queryWrapper.like(questionContent!= null, "question_content", questionContent);
        queryWrapper.eq(questionDifficulty!=null, "question_difficulty", questionDifficulty);
        if (questionType != null){
            questionType.stream().forEach(type -> queryWrapper.eq(type!= null, "question_type", type));
        }
        queryWrapper.orderBy(sortField != null, sortOrder.equals("descend"), sortField);
        return queryWrapper;
    }

    /**
     * 管理员更新题目实现
     * @param questionUpdateDTO
     */
    @Override
    public Question updateQuestion(QuestionUpdateDTO questionUpdateDTO) {
        // 1.获取更新参数
        Long id = questionUpdateDTO.getId();
        ThrowUtil.throwIf(id == null, ErrorCode.PARAMS_ERROR, "题目id不能为空");
        int questionDifficulty = questionUpdateDTO.getQuestionDifficulty();
        QuestionDifficultyEnum questionDifficultyEnum = QuestionDifficultyEnum.getByValue(questionDifficulty);
        ThrowUtil.throwIf(questionDifficultyEnum == null, ErrorCode.PARAMS_ERROR, "题目难度错误");
        String questionName = questionUpdateDTO.getQuestionName();
        String questionContent = questionUpdateDTO.getQuestionContent();
        String questionAnswer = questionUpdateDTO.getQuestionAnswer();
        List<String> questionType = questionUpdateDTO.getQuestionType();
        List<JudgeCase> questionCases = questionUpdateDTO.getQuestionCases();
        List<JudgeCase> questionExample = questionUpdateDTO.getQuestionExample();
        JudgeConfig questionConfig = questionUpdateDTO.getQuestionConfig();
        // 2.设置更新参数，去除空字段
        UpdateWrapper<Question> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set(questionName != null,"question_name", questionName);
        updateWrapper.set(questionContent != null,"question_content", questionContent);
        updateWrapper.set(questionAnswer != null,"question_answer", questionAnswer);
        updateWrapper.set(ObjectUtil.isNotEmpty(questionDifficulty),"question_difficulty", questionDifficulty);
        updateWrapper.set(questionType != null,"question_type", JSONUtil.toJsonStr(questionType));
        updateWrapper.set(questionCases != null,"question_cases", questionCases);
        updateWrapper.set(questionExample != null,"question_example", questionExample);
        updateWrapper.set(questionConfig != null,"question_config", questionConfig);
        boolean update = this.update(updateWrapper);
        ThrowUtil.throwIf(!update, ErrorCode.OPERATION_ERROR);
        return this.getById(id);
    }
}




