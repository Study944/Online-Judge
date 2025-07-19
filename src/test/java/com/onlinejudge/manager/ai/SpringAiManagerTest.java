package com.onlinejudge.manager.ai;

import com.onlinejudge.model.dto.question.QuestionAddDTO;
import com.onlinejudge.service.QuestionService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringAiManagerTest {

    @Resource
    SpringAiManager springAiManager;
    @Resource
    QuestionService questionService;

    @Test
    void test() {
        QuestionAddDTO questionAddDTO = springAiManager.algorithmGeneration("请帮我生成一道中等的的入门算法题");
        System.out.println(questionAddDTO);
        questionService.addQuestion(questionAddDTO);
    }

}