package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Branchs;
import com.springboot.dwxcp.service.BranchsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "党支部模块")
@RestController
@RequestMapping("/api/branch")
public class BranchsController {
    @Autowired
    private BranchsService branchsService;

    @ApiOperation("查询所有党支部列表")
    @GetMapping(value = "/list")
    private BaseResponse getBranchsList(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize) {

        System.out.println("----------------查询所有党支部列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<Branchs> list = null;
        PageInfo page = null;
        try {
            list = branchsService.getBranchsList(pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("单个新增")
    @PostMapping(value = "/add")
    private BaseResponse addBranchs(@RequestBody Branchs branchs) {
        System.out.println("----------------单个新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = branchsService.addBranchs(branchs);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("删除党支部")
    @DeleteMapping(value = "/delete")
    private BaseResponse deleteBranchsById(@RequestParam("id") int id) {
        System.out.println("----------------删除党支部------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = branchsService.deleteBranchsById(id);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("编辑党支部")
    @PostMapping(value = "/edit")
    private BaseResponse editBranchsById(@RequestBody Branchs branchs) {
        System.out.println("----------------编辑党支部------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = branchsService.editBranchsById(branchs);
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
    private BaseResponse batchAddBranchs(@RequestBody List<Branchs> branchsList) {
        System.out.println("----------------批量新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = branchsService.batchAddBranchs(branchsList);
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
