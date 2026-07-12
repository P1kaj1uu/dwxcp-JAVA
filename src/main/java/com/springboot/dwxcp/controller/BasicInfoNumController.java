package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.BasicInfoNum;
import com.springboot.dwxcp.service.BasicInfoNumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "基本情况人员数量年龄模块")
@RestController
@RequestMapping("/api/basic-info-num")
public class BasicInfoNumController {
    @Autowired
    private BasicInfoNumService basicInfoNumService;

    @ApiOperation("查询基本信息人员年龄等情况")
    @GetMapping(value = "/list")
    private BaseResponse getBasicInfoNumList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<BasicInfoNum> page = basicInfoNumService.getBasicInfoNumList(pageNum, pageSize);
        return new BaseResponse<>(StatusCode.Success, page);
    }

    @ApiOperation("新增")
    @PostMapping(value = "/add")
    private BaseResponse addBasicInfoNum(@RequestBody BasicInfoNum basicInfoNum) {
        System.out.println("----------------新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = basicInfoNumService.addBasicInfoNum(basicInfoNum);
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
    private BaseResponse editBasicInfoNumById(@RequestBody BasicInfoNum basicInfoNum) {
        System.out.println("----------------编辑------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = basicInfoNumService.editBasicInfoNumById(basicInfoNum);
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
