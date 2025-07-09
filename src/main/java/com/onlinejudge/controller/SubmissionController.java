package com.onlinejudge.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.onlinejudge.common.BaseResponse;
import com.onlinejudge.common.ResultUtil;
import com.onlinejudge.common.ThrowUtil;
import com.onlinejudge.common.UserContext;
import com.onlinejudge.exception.ErrorCode;
import com.onlinejudge.model.dto.submission.SubmissionAddDTO;
import com.onlinejudge.model.dto.submission.SubmissionPageDTO;
import com.onlinejudge.model.entity.User;
import com.onlinejudge.model.entity.submission.Submission;
import com.onlinejudge.service.SubmissionService;
import com.onlinejudge.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Resource
    private SubmissionService submissionService;
    @Resource
    private UserService userService;

    /**
     * 提交代码
     * @param submissionAddDTO
     */
    @PostMapping("/submit")
    public BaseResponse<String> submit(@RequestBody SubmissionAddDTO submissionAddDTO) {
        ThrowUtil.throwIf(submissionAddDTO == null, ErrorCode.PARAMS_ERROR);
        String res = submissionService.submit(submissionAddDTO);
        return ResultUtil.success(res);
    }

    /**
     * 获取所有提交列表
     * @param submissionQueryDTO
     */
    @PostMapping("/list")
    public BaseResponse<Page<Submission>> list(@RequestBody SubmissionPageDTO submissionQueryDTO) {
        ThrowUtil.throwIf(submissionQueryDTO == null, ErrorCode.PARAMS_ERROR);
        Integer current = submissionQueryDTO.getCurrent();
        Integer size = submissionQueryDTO.getSize();
        Page<Submission> page = submissionService.page(new Page<>(current, size),
                submissionService.getQueryWrapper(submissionQueryDTO));
        return ResultUtil.success(page);
    }

    /**
     * 获取当前用户所有提交列表
     */
    @GetMapping("/list/my")
    public BaseResponse<Page<Submission>> listMy() {
        User user = userService.getById(UserContext.getUserId());
        SubmissionPageDTO submissionPageDTO = new SubmissionPageDTO();
        submissionPageDTO.setUserName(user.getUserName());
        Page<Submission> page = submissionService.page(new Page<>(1, 10),
                submissionService.getQueryWrapper(submissionPageDTO));
        return ResultUtil.success(page);
    }

}
