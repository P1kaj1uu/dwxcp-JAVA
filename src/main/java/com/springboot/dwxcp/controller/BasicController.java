package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Basic;
import com.springboot.dwxcp.service.BasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "基本情况模块")
@RestController
@RequestMapping("/api/basic")
public class BasicController {
    @Autowired
    private BasicService basicService;

    @ApiOperation("查询所有基本情况列表")
    @GetMapping(value = "/list")
    private BaseResponse getBasicList(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<Basic> page = basicService.getBasicList(pageNum, pageSize);
        return new BaseResponse<>(StatusCode.Success, page);
    }

    @ApiOperation("按部门筛选")
    @GetMapping(value = "/find")
    private BaseResponse getBasicByType(@RequestParam("type") String type,
                                        @RequestParam(defaultValue = "1") int pageNum,
                                        @RequestParam(defaultValue = "20") int pageSize) {
        PageInfo<Basic> page = basicService.getBasicByType(type, pageNum, pageSize);
        return new BaseResponse<>(StatusCode.Success, page);
    }

    @ApiOperation("上传照片")
    @PostMapping("/upload-photo")
    public BaseResponse uploadPhoto(@RequestParam("file") MultipartFile file, @RequestParam("basicId") Long basicId) {
        System.out.println("----------------上传照片------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        try {
            if (file.isEmpty()) {
                return new BaseResponse(StatusCode.Fail.getCode(), "文件不能为空");
            }
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || (!originalFilename.toLowerCase().endsWith(".jpg")
                    && !originalFilename.toLowerCase().endsWith(".jpeg")
                    && !originalFilename.toLowerCase().endsWith(".png")
                    && !originalFilename.toLowerCase().endsWith(".gif"))) {
                return new BaseResponse(StatusCode.Fail.getCode(), "只支持上传 JPG、PNG、GIF 格式的图片");
            }

            Basic basic = new Basic();
            basic.setId(basicId);
            basic.setPhoto(file.getBytes());

            boolean flag = basicService.uploadPhoto(basic);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "上传失败，请返回重试");
            }
        } catch (IOException e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    @ApiOperation("预览照片")
    @GetMapping("/preview-photo")
    public void previewPhoto(@RequestParam("id") Long id, HttpServletResponse httpResponse) {
        Basic basic = basicService.selectBasicById(id);
        if (basic == null || basic.getPhoto() == null) {
            return;
        }
        String contentType = "image/jpeg";
        httpResponse.setContentType(contentType);
        try {
            httpResponse.getOutputStream().write(basic.getPhoto());
            httpResponse.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("新增")
    @PostMapping(value = "/add")
    private BaseResponse addBasic(@RequestBody Basic basic) {
        System.out.println("----------------新增------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = basicService.addBasic(basic);
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
    private BaseResponse deleteBasicById(@RequestParam("id") int id) {
        System.out.println("----------------删除------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = basicService.deleteBasicById(id);
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
    private BaseResponse editBranchsById(@RequestBody Basic basic) {
        System.out.println("----------------编辑------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = basicService.editBasicById(basic);
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
