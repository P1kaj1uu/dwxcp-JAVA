package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.PartyBranchEvaluation;
import com.springboot.dwxcp.service.PartyBranchEvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "党小组评价模块")
@RestController
@RequestMapping("/api/party-branch/evaluation")
public class PartyBranchEvaluationController {
    @Autowired
    private PartyBranchEvaluationService partyBranchEvaluationService;

    @ApiOperation("查询党员创岗建区季度评议汇总表 - 党小组评价列表")
    @GetMapping(value = "/list")
    private BaseResponse getPartyBranchEvaluationList(
            @RequestParam(required = false) String partyBranch,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String quarter,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize) {

        System.out.println("----------------查询党员创岗建区季度评议汇总表 - 党小组评价列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<PartyBranchEvaluation> list = null;
        PageInfo page = null;
        try {
            list = partyBranchEvaluationService.getPartyBranchEvaluationList(partyBranch, year, quarter, pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("新增")
    @PostMapping(value = "/add")
    private BaseResponse addPartyBranchEvaluation(@RequestBody PartyBranchEvaluation partyBranchEvaluation) {
        System.out.println("----------------新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = partyBranchEvaluationService.addPartyBranchEvaluation(partyBranchEvaluation);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/delete")
    private BaseResponse deletePartyBranchEvaluationById(@RequestParam("id") int id) {
        System.out.println("----------------删除------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = partyBranchEvaluationService.deletePartyBranchEvaluationById(id);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("编辑")
    @PostMapping(value = "/edit")
    private BaseResponse editPartyBranchEvaluationById(@RequestBody PartyBranchEvaluation partyBranchEvaluation) {
        System.out.println("----------------编辑------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = partyBranchEvaluationService.editPartyBranchEvaluationById(partyBranchEvaluation);
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
