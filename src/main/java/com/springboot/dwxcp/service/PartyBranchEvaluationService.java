package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.PartyBranchEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartyBranchEvaluationService {
    // 查询党员创岗建区季度评议汇总表 - 党小组评价列表
    List<PartyBranchEvaluation> getPartyBranchEvaluationList(
            @Param("partyBranch") String partyBranch,
            @Param("year") String year,
            @Param("quarter") String quarter,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 新增
    boolean addPartyBranchEvaluation(PartyBranchEvaluation partyBranchEvaluation);

    // 删除
    boolean deletePartyBranchEvaluationById(int id);

    // 编辑
    boolean editPartyBranchEvaluationById(PartyBranchEvaluation partyBranchEvaluation);
}
