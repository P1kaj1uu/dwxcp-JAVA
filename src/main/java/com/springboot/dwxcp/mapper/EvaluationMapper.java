package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationMapper extends BaseMapper<Evaluation> {
    List<Evaluation> getEvaluationList(
            @Param("name") String name,
            @Param("responsibilityPost") String responsibilityPost,
            @Param("responsibilityArea") String responsibilityArea,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    boolean addEvaluation(Evaluation evaluation);

    boolean deleteEvaluationById(int id);

    boolean editEvaluationById(Evaluation evaluation);

    boolean batchAddEvaluation(@Param("list") List<Evaluation> evaluationList);
}
