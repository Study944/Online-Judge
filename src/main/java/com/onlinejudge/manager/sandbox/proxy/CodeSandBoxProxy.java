package com.onlinejudge.manager.sandbox.proxy;

import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CodeSandBoxProxy implements CodeSandBox {

    private final CodeSandBox codeSandBox;

    public CodeSandBoxProxy(CodeSandBox codeSandBox) {
        this.codeSandBox = codeSandBox;
    }

    @Override
    public CodeSandBoxResult runCode(CodeSandBoxDTO codeSandBoxDTO) {
        log.info("代码沙箱请求参数：{}", codeSandBoxDTO);
        CodeSandBoxResult codeSandBoxResult = codeSandBox.runCode(codeSandBoxDTO);
        log.info("代码沙箱返回结果：{}", codeSandBoxResult);
        return codeSandBoxResult;
    }
}
