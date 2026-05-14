package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.EvaluationResult;
import com.springboot.dwxcp.service.EvaluationResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "考核本支部结果模块")
@RestController
@RequestMapping("/api/result")
public class EvaluationResultController {
    @Autowired
    private EvaluationResultService evaluationResultService;

    @ApiOperation("查询考核本支部结果列表")
    @GetMapping(value = "/list")
    private BaseResponse getEvaluationResultList(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize) {

        System.out.println("----------------查询考核本支部结果列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<EvaluationResult> list = null;
        PageInfo page = null;
        try {
            list = evaluationResultService.getEvaluationResultList(pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("新增")
    @PostMapping(value = "/add")
    private BaseResponse addEvaluationResult(@RequestBody EvaluationResult evaluationResult) {
        System.out.println("----------------新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = evaluationResultService.addEvaluationResult(evaluationResult);
            System.out.println(evaluationResult);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("编辑")
    @PostMapping(value = "/edit")
    private BaseResponse editEvaluationResultById(@RequestBody EvaluationResult evaluationResult) {
        System.out.println("----------------编辑------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = evaluationResultService.editEvaluationResultById(evaluationResult);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "编辑失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }
}
