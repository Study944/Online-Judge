package com.onlinejudge.controller;

import com.onlinejudge.common.BaseResponse;
import com.onlinejudge.common.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthController {

    @GetMapping("/health")
    public BaseResponse<String> health() {
        return ResultUtil.success("健康");
    }

}
