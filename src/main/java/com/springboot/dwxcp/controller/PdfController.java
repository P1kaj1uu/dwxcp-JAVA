package com.springboot.dwxcp.controller;

import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import com.springboot.dwxcp.entity.Pdf;
import com.springboot.dwxcp.service.PdfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "PDF文件模块")
@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @ApiOperation("上传PDF文件")
    @PostMapping("/upload")
    public BaseResponse uploadPdf(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        System.out.println("----------------上传PDF文件------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        try {
            if (file.isEmpty()) {
                return new BaseResponse(StatusCode.Fail.getCode(), "文件不能为空");
            }
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !originalFilename.toLowerCase().endsWith(".pdf")) {
                return new BaseResponse(StatusCode.Fail.getCode(), "只支持上传PDF文件");
            }

            Pdf pdf = new Pdf();
            pdf.setFileName(originalFilename);
            pdf.setFileType(type);
            pdf.setFileContent(file.getBytes());

            boolean flag = pdfService.uploadPdf(pdf);
            if (!flag) {
                response = new BaseResponse(StatusCode.Fail.getCode(), "上传失败，请返回重试");
            }
            response.setData(pdf);
        } catch (IOException e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        return response;
    }

    @ApiOperation("获取PDF文件列表")
    @GetMapping("/list")
    public BaseResponse selectPdfList(@RequestParam("type") String type) {
        System.out.println("----------------获取PDF文件列表------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        List<Pdf> list = null;
        try {
            list = pdfService.selectPdfList(type);
        } catch (Exception e) {
            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
        }
        response.setData(list);
        return response;
    }

    @ApiOperation("预览/下载PDF文件")
    @GetMapping("/preview")
    public void previewPdf(@RequestParam("id") Long id, HttpServletResponse httpResponse) {
        Pdf pdf = pdfService.selectPdfById(id);
        if (pdf == null || pdf.getFileContent() == null) {
            return;
        }
        httpResponse.setContentType("application/pdf");
        httpResponse.setHeader("Content-Disposition", "inline; filename=" + pdf.getFileName());
        try {
            httpResponse.getOutputStream().write(pdf.getFileContent());
            httpResponse.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("删除PDF文件")
    @DeleteMapping("/delete")
    public BaseResponse deletePdf(@RequestParam("id") Long id) {
        System.out.println("----------------删除PDF文件------------------");
        BaseResponse response = new BaseResponse<>(StatusCode.Success);
        boolean flag = pdfService.deletePdfById(id);
        if (!flag) {
            response = new BaseResponse(StatusCode.Fail.getCode(), "删除失败，请返回重试");
        }
        response.setData(flag);
        return response;
    }
}
