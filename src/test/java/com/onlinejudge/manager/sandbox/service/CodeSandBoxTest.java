package com.onlinejudge.manager.sandbox.service;

import com.onlinejudge.manager.sandbox.SandBoxFactory;
import com.onlinejudge.manager.sandbox.impl.TestCodeSandBoxImpl;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodeSandBoxTest {
    @Value("${sandbox.type}")
    private String sandboxType = "test";

    @Test
    public void testSandBox(){
        // 手动创建
        CodeSandBox codeSandBox = new TestCodeSandBoxImpl();
        CodeSandBoxResult codeSandBoxResult = codeSandBox.runCode(new CodeSandBoxDTO());
        // 工厂创建
        CodeSandBox codeSandBox2 = SandBoxFactory.getSandBox("remote");
        CodeSandBoxResult codeSandBoxResult2 = codeSandBox2.runCode(new CodeSandBoxDTO());
        // 配置文件创建
        CodeSandBox codeSandBox3 = SandBoxFactory.getSandBox(sandboxType);
        CodeSandBoxResult codeSandBoxResult3 = codeSandBox3.runCode(new CodeSandBoxDTO());
    }

}