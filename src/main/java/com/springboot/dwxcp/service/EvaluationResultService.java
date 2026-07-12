package com.springboot.dwxcp.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.EvaluationResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationResultService {
    // 查询考核本支部结果列表（分页）
    PageInfo<EvaluationResult> getEvaluationResultList(@Param("pageNum") int pageNum,
                                                       @Param("pageSize") int pageSize);

    // 新增
    boolean addEvaluationResult(EvaluationResult evaluationResult);

    // 编辑
    boolean editEvaluationResultById(EvaluationResult evaluationResult);
}
