package com.springboot.dwxcp.controller;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.ShowMedia;
import com.springboot.dwxcp.service.ShowMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "主页多媒体展示图片还是视频模块")
@RestController
@RequestMapping("/api/show-media")
public class ShowMediaController {
    @Autowired
    private ShowMediaService showMediaService;

    @ApiOperation("返回主页多媒体展示图片还是视频")
    @GetMapping(value = "/list")
    private BaseResponse getEvaluationList() {
        List<ShowMedia> list = showMediaService.getShowMediaList();
        return new BaseResponse<>(StatusCode.Success, list);
    }

    @ApiOperation("编辑")
    @PostMapping(value = "/edit")
    private BaseResponse editShowMediaById(@RequestBody ShowMedia showMedia) {
        System.out.println("----------------编辑------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = false;
        try {
            flag = showMediaService.editShowMediaById(showMedia);
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
