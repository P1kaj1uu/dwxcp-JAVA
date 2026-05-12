package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Evaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationService {
    // 查询党员创岗建区季度评议汇总表列表
    List<Evaluation> getEvaluationList(
            @Param("name") String name,
            @Param("responsibilityPost") String responsibilityPost,
            @Param("responsibilityArea") String responsibilityArea,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 新增单个评价
    boolean addEvaluation(Evaluation evaluation);

    // 删除评价
    boolean deleteEvaluationById(int id);

    // 编辑评价
    boolean editEvaluationById(Evaluation evaluation);

    // 批量新增评价
    boolean batchAddEvaluation(@Param("list") List<Evaluation> evaluationList);
}
