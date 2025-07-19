package com.onlinejudge.manager.sandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxDTO;
import com.onlinejudge.manager.sandbox.model.CodeSandBoxResult;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;
import org.springframework.stereotype.Service;

/**
 * 远程代码沙箱
 */
@Service
public class RemoteCodeSandBoxImpl implements CodeSandBox {
    @Override
    public CodeSandBoxResult runCode(CodeSandBoxDTO codeSandBoxDTO) {
        String jsonStr = JSONUtil.toJsonStr(codeSandBoxDTO);
        String body = HttpUtil.createPost("http://192.168.67.129:8777/sandbox/run")
                .body(jsonStr)
                .execute()
                .body();
        System.out.println(body);
        return JSONUtil.toBean(body, CodeSandBoxResult.class);
    }
}
