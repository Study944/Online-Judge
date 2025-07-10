package com.onlinejudge.manager.sandbox.service;

import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;

/**
 * 代码沙箱接口
 */
public interface CodeSandBox {

    /**
     * 沙箱执行代码请求
     * @param codeSandBoxDTO
     */
    public CodeSandBoxResult runCode(CodeSandBoxDTO codeSandBoxDTO);

}
