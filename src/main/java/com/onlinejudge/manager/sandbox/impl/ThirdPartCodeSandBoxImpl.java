package com.onlinejudge.manager.sandbox.impl;

import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;
import org.springframework.stereotype.Service;

/**
 * 第三方代码沙箱
 */
@Service
public class ThirdPartCodeSandBoxImpl implements CodeSandBox {
    @Override
    public CodeSandBoxResult runCode(CodeSandBoxDTO codeSandBoxDTO) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
