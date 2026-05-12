package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Groups;
import com.springboot.dwxcp.service.GroupsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "党小组模块")
@RestController
@RequestMapping("/api/group")
public class GroupsController {
    @Autowired
    private GroupsService groupsService;

    @ApiOperation("查询所有党小组列表")
    @GetMapping(value = "/list")
    private BaseResponse getGroupsList(
            @RequestParam(required = false) String party,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String name1,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize) {

        System.out.println("----------------查询所有党小组列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<Groups> list = null;
        PageInfo page = null;
        try {
            list = groupsService.getGroupsList(party, name, name1, pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("单个新增")
    @PostMapping(value = "/add")
    private BaseResponse addGroups(@RequestBody Groups groups) {
        System.out.println("----------------单个新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = groupsService.addGroups(groups);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("删除党小组")
    @DeleteMapping(value = "/delete")
    private BaseResponse deleteGroupsById(@RequestParam("id") int id) {
        System.out.println("----------------删除党小组------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = groupsService.deleteGroupsById(id);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("编辑党小组")
    @PostMapping(value = "/edit")
    private BaseResponse editGroupsById(@RequestBody Groups groups) {
        System.out.println("----------------编辑党小组------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = groupsService.editGroupsById(groups);
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
    private BaseResponse batchAddGroups(@RequestBody List<Groups> groupsList) {
        System.out.println("----------------批量新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = groupsService.batchAddGroups(groupsList);
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
