package com.onlinejudge.manager.sandbox.impl;

import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;
import com.onlinejudge.model.entity.submission.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * 测试代码沙箱
 */
@Service
public class TestCodeSandBoxImpl implements CodeSandBox {
    @Override
    public CodeSandBoxResult runCode(CodeSandBoxDTO codeSandBoxDTO) {
        CodeSandBoxResult codeSandBoxResult = new CodeSandBoxResult();
        codeSandBoxResult.setOutput(codeSandBoxDTO.getInput());
        codeSandBoxResult.setMessage("测试中");
        codeSandBoxResult.setStatus(1);
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        codeSandBoxResult.setJudgeInfo(judgeInfo);
        return codeSandBoxResult;
    }
}
