package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.EvaluationResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationResultMapper extends BaseMapper<EvaluationResult> {
    List<EvaluationResult> getEvaluationResultList();

    boolean addEvaluationResult(EvaluationResult evaluationResult);

    boolean editEvaluationResultById(EvaluationResult evaluationResult);
}
