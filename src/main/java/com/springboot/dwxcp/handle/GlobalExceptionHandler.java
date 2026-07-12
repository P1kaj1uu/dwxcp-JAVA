package com.springboot.dwxcp.handle;

import com.springboot.dwxcp.common.BaseResponse;
import com.springboot.dwxcp.common.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 替代各 Controller 中重复的 try/catch，统一返回 BaseResponse
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 参数绑定异常（@RequestBody + @Valid 校验失败） */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest req) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.warn("[参数校验失败] {} {} -> {}", req.getMethod(), req.getRequestURI(), msg);
        return new BaseResponse(StatusCode.InvalidParams.getCode(), msg);
    }

    /** 表单绑定异常 */
    @ExceptionHandler(BindException.class)
    public BaseResponse handleBindException(BindException e, HttpServletRequest req) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.warn("[参数绑定失败] {} {} -> {}", req.getMethod(), req.getRequestURI(), msg);
        return new BaseResponse(StatusCode.InvalidParams.getCode(), msg);
    }

    /** 单个参数校验失败（@RequestParam + @Validated） */
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleConstraintViolation(ConstraintViolationException e, HttpServletRequest req) {
        String msg = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        log.warn("[参数约束失败] {} {} -> {}", req.getMethod(), req.getRequestURI(), msg);
        return new BaseResponse(StatusCode.InvalidParams.getCode(), msg);
    }

    /** 缺少必填参数 */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleMissingParam(MissingServletRequestParameterException e, HttpServletRequest req) {
        log.warn("[缺少参数] {} {} -> {}", req.getMethod(), req.getRequestURI(), e.getMessage());
        return new BaseResponse(StatusCode.InvalidParams.getCode(), "缺少必填参数: " + e.getParameterName());
    }

    /** 请求体解析失败 */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleNotReadable(HttpMessageNotReadableException e, HttpServletRequest req) {
        log.warn("[请求体解析失败] {} {}", req.getMethod(), req.getRequestURI());
        return new BaseResponse(StatusCode.InvalidParams.getCode(), "请求体格式错误");
    }

    /** 数据库查询超时 —— 慢查询典型场景 */
    @ExceptionHandler(QueryTimeoutException.class)
    public BaseResponse handleQueryTimeout(QueryTimeoutException e, HttpServletRequest req) {
        log.error("[数据库查询超时] {} {} -> {}", req.getMethod(), req.getRequestURI(), e.getMessage());
        return new BaseResponse(StatusCode.Fail.getCode(), "查询超时，请稍后重试");
    }

    /** 数据库异常 */
    @ExceptionHandler(DataAccessException.class)
    public BaseResponse handleDataAccess(DataAccessException e, HttpServletRequest req) {
        log.error("[数据库异常] {} {}", req.getMethod(), req.getRequestURI(), e);
        // 不向前端透出 SQL 细节，避免泄露表结构
        return new BaseResponse(StatusCode.Fail.getCode(), "数据访问异常");
    }

    /** 上传文件过大 */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public BaseResponse handleUploadSize(MaxUploadSizeExceededException e) {
        log.warn("[上传文件过大] {}", e.getMessage());
        return new BaseResponse(StatusCode.Fail.getCode(), "文件超过 50MB 上限");
    }

    /** 算术异常（如分页参数 0） */
    @ExceptionHandler(ArithmeticException.class)
    public BaseResponse handleArithmetic(ArithmeticException e, HttpServletRequest req) {
        log.warn("[算术异常] {} {} -> {}", req.getMethod(), req.getRequestURI(), e.getMessage());
        return new BaseResponse(StatusCode.InvalidParams.getCode(), "参数计算异常: " + e.getMessage());
    }

    /** 兜底：未捕获异常 */
    @ExceptionHandler(Exception.class)
    public BaseResponse handleUnknown(Exception e, HttpServletRequest req) {
        log.error("[未捕获异常] {} {}", req.getMethod(), req.getRequestURI(), e);
        return new BaseResponse(StatusCode.UnknownError);
    }
}