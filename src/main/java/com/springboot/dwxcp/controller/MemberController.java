package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Member;
import com.springboot.dwxcp.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "党员模块")
@RestController
@RequestMapping("/api/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @ApiOperation("查询所有党员列表")
    @GetMapping(value = "/list")
    private BaseResponse getMemberList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) String groups,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize) {

        System.out.println("----------------查询所有党员列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<Member> list = null;
        PageInfo page = null;
        try {
            list = memberService.getMemberList(name, condition, groups, pageNum, pageSize);
            page = new PageInfo(list);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(page);
        return response;
    }

    @ApiOperation("单个新增")
    @PostMapping(value = "/add")
    private BaseResponse addMember(@RequestBody Member member) {
        System.out.println("----------------单个新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = memberService.addMember(member);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "新增失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("删除党员")
    @DeleteMapping(value = "/delete")
    private BaseResponse deleteMemberById(@RequestParam("id") int id) {
        System.out.println("----------------删除党员------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = memberService.deleteMemberById(id);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(flag);
        return response;
    }

    @ApiOperation("编辑党员")
    @PostMapping(value = "/edit")
    private BaseResponse editMemberById(@RequestBody Member member) {
        System.out.println("----------------编辑党员------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = memberService.editMemberById(member);
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
    private BaseResponse batchAddEvaluation(@RequestBody List<Member> memberList) {
        System.out.println("----------------批量新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = memberService.batchAddMember(memberList);
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
