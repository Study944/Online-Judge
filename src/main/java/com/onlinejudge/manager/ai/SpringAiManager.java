package com.onlinejudge.manager.ai;

import com.onlinejudge.model.dto.question.QuestionAddDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Component;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;

/**
 * AI管理器
 */
@Component
@Slf4j
public class SpringAiManager {

    private static final String SystemPrompt =
            "你是一位经验丰富的算法专家，专注于编程竞赛题目的生成与分析。"
                    + "你的任务包括："
                    + "1. 生成结构清晰、描述明确、输入输出格式规范且包含约束条件的编程题目；"
                    + "2. 根据题目内容推荐合适的标签（例如：'动态规划'、'图论'、'二分查找'等）；"
                    + "3. 生成测试数据，包括样例输入输出、边界情况以及隐藏测试用例；"
                    + "4. 分析用户编写的解题代码，提供反馈意见，并提出改进建议或修正方案。"
                    + "5. 根据题目内容生成题目配置信息questionConfig，judgeConfig合适的时间/ms和内存限制/KB；"
                    + "6. 生成题目同时生成实例答案代码只需要Java语言的；"
                    + "请以简洁、准确、结构化的方式回答，确保内容适合集成到在线判题系统中。";
    private final ChatClient chatClient;

    private SpringAiManager(ChatModel dashscopeChatModel) {
        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SystemPrompt)
                .defaultAdvisors(
                        new MessageChatMemoryAdvisor(chatMemory)
                )
                .build();
    }

    public QuestionAddDTO algorithmGeneration(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .entity(QuestionAddDTO.class);
    }
}