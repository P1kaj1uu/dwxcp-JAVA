package com.springboot.dwxcp.controller;

import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Media;
import com.springboot.dwxcp.service.MediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "媒体文件模块（图片/视频）")
@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @ApiOperation("上传媒体文件（图片/视频）")
    @PostMapping("/upload")
    public BaseResponse uploadMedia(@RequestParam("file") MultipartFile file,
                                    @RequestParam("type") String type,
                                    @RequestParam("category") String category) {
        System.out.println("----------------上传媒体文件------------------");
        System.out.println("文件名: " + file.getOriginalFilename());
        System.out.println("类型: " + type);
        System.out.println("分类: " + category);

        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        try {
            if (file.isEmpty()) {
                return new BaseResponse(StatusCode.Fail.getCode(), "文件不能为空");
            }

            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();

            // 验证文件类型（图片或视频）
            if (contentType == null ||
                    (!contentType.startsWith("image/") && !contentType.startsWith("video/"))) {
                return new BaseResponse(StatusCode.Fail.getCode(), "只支持上传图片或视频文件");
            }

            // 验证文件大小（视频限制100MB，图片限制10MB）
            long maxSize = contentType.startsWith("video/") ? 100 * 1024 * 1024 : 10 * 1024 * 1024;
            if (file.getSize() > maxSize) {
                String typeName = contentType.startsWith("video/") ? "视频" : "图片";
                return new BaseResponse(StatusCode.Fail.getCode(), typeName + "文件大小不能超过" + (maxSize / 1024 / 1024) + "MB");
            }

            Media media = new Media();
            media.setFileName(originalFilename);
            media.setFileType(type);
            media.setCategory(category);
            byte[] fileContent = file.getBytes();
            System.out.println("文件大小: " + fileContent.length + " bytes");
            media.setFileContent(fileContent);
            // 可以设置访问URL，例如：/api/media/preview?id={id}
            media.setFileUrl("/api/media/preview?id=");

            boolean flag = mediaService.uploadMedia(media);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "上传失败，请返回重试");
            } else {
                System.out.println("上传成功，文件ID: " + media.getId());
            }
            response.setData(media);
        } catch (IOException e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @ApiOperation("获取媒体文件列表")
    @GetMapping("/list")
    public BaseResponse selectMediaList(@RequestParam("category") String category) {
        System.out.println("----------------获取媒体文件列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<Media> list = null;
        try {
            list = mediaService.selectMediaList(category);
            // 设置完整的访问URL
            for (Media media : list) {
                media.setFileUrl("/api/media/preview?id=" + media.getId());
                System.out.println("文件: " + media.getFileName() + ", ID: " + media.getId() + ", 类型: " + media.getFileType());
            }
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
            e.printStackTrace();
        }
        response.setData(list);
        return response;
    }

    @ApiOperation("预览媒体文件（图片/视频）")
    @GetMapping("/preview")
    public void previewMedia(@RequestParam("id") Long id, HttpServletResponse response) {
        System.out.println("----------------预览媒体文件------------------");
        System.out.println("请求ID: " + id);

        try {
            Media media = mediaService.selectMediaById(id);
            if (media == null) {
                System.out.println("媒体文件不存在，ID: " + id);
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Media not found");
                return;
            }

            if (media.getFileContent() == null) {
                System.out.println("文件内容为空，ID: " + id + ", 文件名: " + media.getFileName());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("File content is empty");
                return;
            }

            String fileName = media.getFileName();
            String contentType = getContentType(fileName);
            System.out.println("文件名: " + fileName);
            System.out.println("Content-Type: " + contentType);
            System.out.println("文件大小: " + media.getFileContent().length + " bytes");

            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.setContentLength(media.getFileContent().length);

            response.getOutputStream().write(media.getFileContent());
            response.getOutputStream().flush();
            System.out.println("文件预览成功");
        } catch (Exception e) {
            System.err.println("预览文件失败，ID: " + id);
            e.printStackTrace();
            try {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Error: " + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 根据文件名获取Content-Type
     */
    private String getContentType(String fileName) {
        if (fileName == null) {
            return "application/octet-stream";
        }
        String lowerName = fileName.toLowerCase();
        if (lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (lowerName.endsWith(".png")) {
            return "image/png";
        } else if (lowerName.endsWith(".gif")) {
            return "image/gif";
        } else if (lowerName.endsWith(".bmp")) {
            return "image/bmp";
        } else if (lowerName.endsWith(".mp4")) {
            return "video/mp4";
        } else if (lowerName.endsWith(".webm")) {
            return "video/webm";
        } else if (lowerName.endsWith(".ogg")) {
            return "video/ogg";
        } else {
            return "application/octet-stream";
        }
    }

    @ApiOperation("删除媒体文件")
    @DeleteMapping("/delete")
    public BaseResponse deleteMedia(@RequestParam("id") Long id) {
        System.out.println("----------------删除媒体文件------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = mediaService.deleteMediaById(id);
        if (!flag) {
            response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
        }
        response.setData(flag);
        return response;
    }
}