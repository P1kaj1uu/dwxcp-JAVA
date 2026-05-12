package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Evaluation;
import com.springboot.dwxcp.service.EvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "评价模块")
@RestController
@RequestMapping("/api/evaluation")
public class EvaluationController {
    @Autowired
    private EvaluationService evaluationService;

    @ApiOperation("查询所有评价列表")
    @GetMapping(value = "/list")
    private BaseResponse getEvaluationList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String responsibilityPost,
            @RequestParam(required = false) String responsibilityArea,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize) {

        System.out.println("----------------查询所有评价列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<Evaluation> list = null;
        PageInfo page = null;
        try {
            list = evaluationService.getEvaluationList(name, responsibilityPost, responsibilityArea, pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("单个新增")
    @PostMapping(value = "/add")
    private BaseResponse addUser(@RequestBody Evaluation evaluation) {
        System.out.println("----------------单个新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = evaluationService.addEvaluation(evaluation);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("删除评价")
    @DeleteMapping(value = "/delete")
    private BaseResponse deleteUserById(@RequestParam("id") int id) {
        System.out.println("----------------删除评价------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = evaluationService.deleteEvaluationById(id);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("编辑评价")
    @PostMapping(value = "/edit")
    private BaseResponse editEvaluationById(@RequestBody Evaluation evaluation) {
        System.out.println("----------------编辑评价------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = evaluationService.editEvaluationById(evaluation);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "编辑失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("批量新增")
    @PostMapping(value = "/batch/add")
    private BaseResponse batchAddEvaluation(@RequestBody List<Evaluation> evaluationList) {
        System.out.println("----------------批量新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = evaluationService.batchAddEvaluation(evaluationList);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "批量新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }
}
