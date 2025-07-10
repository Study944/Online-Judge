package com.onlinejudge.manager.sandbox;

import com.onlinejudge.manager.sandbox.impl.RemoteCodeSandBoxImpl;
import com.onlinejudge.manager.sandbox.impl.TestCodeSandBoxImpl;
import com.onlinejudge.manager.sandbox.impl.ThirdPartCodeSandBoxImpl;
import com.onlinejudge.manager.sandbox.service.CodeSandBox;

/**
 * 沙箱工厂
 */
public class SandBoxFactory {

    public static CodeSandBox getSandBox(String type) {
        // 根据不同的参数获取不同的沙箱
        if ("remote".equals(type)) {
            return new RemoteCodeSandBoxImpl();
        } else if ("test".equals(type)) {
            return new TestCodeSandBoxImpl();
        } else if ("third_part".equals(type)) {
            return new ThirdPartCodeSandBoxImpl();
        }
        return null;
    }

}
