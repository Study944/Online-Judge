package com.onlinejudge.manager.sandbox.impl;

import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;
import com.onlinejudge.model.entity.submission.JudgeInfo;
import org.springframework.stereotype.Service;

/**
 * 远程代码沙箱
 */
@Service
public class RemoteCodeSandBoxImpl implements CodeSandBox {
    @Override
    public CodeSandBoxResult runCode(CodeSandBoxDTO codeSandBoxDTO) {
        System.out.println("远程代码沙箱");
        return null;
    }
}
