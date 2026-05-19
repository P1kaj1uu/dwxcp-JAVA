package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.PartyBranchEvaluation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartyBranchEvaluationMapper extends BaseMapper<PartyBranchEvaluation> {
    List<PartyBranchEvaluation> getPartyBranchEvaluationList(
            @Param("partyBranch") String partyBranch,
            @Param("year") String year,
            @Param("quarter") String quarter,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    boolean addPartyBranchEvaluation(PartyBranchEvaluation partyBranchEvaluation);

    boolean deletePartyBranchEvaluationById(int id);

    boolean editPartyBranchEvaluationById(PartyBranchEvaluation partyBranchEvaluation);
}
