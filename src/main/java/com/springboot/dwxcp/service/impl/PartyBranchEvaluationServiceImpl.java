package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.PartyBranchEvaluation;
import com.springboot.dwxcp.mapper.PartyBranchEvaluationMapper;
import com.springboot.dwxcp.service.PartyBranchEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyBranchEvaluationServiceImpl extends ServiceImpl<PartyBranchEvaluationMapper, PartyBranchEvaluation> implements PartyBranchEvaluationService {
    @Autowired
    private PartyBranchEvaluationMapper partyBranchEvaluationMapper;

    @Override
    public List<PartyBranchEvaluation> getPartyBranchEvaluationList(String partyBranch, String year, String quarter, int pageNum, int pageSize) {
        return partyBranchEvaluationMapper.getPartyBranchEvaluationList(partyBranch, year, quarter, pageNum, pageSize);
    }

    @Override
    public boolean addPartyBranchEvaluation(PartyBranchEvaluation partyBranchEvaluation) {
        return partyBranchEvaluationMapper.addPartyBranchEvaluation(partyBranchEvaluation);
    }

    @Override
    public boolean deletePartyBranchEvaluationById(int id) {
        return partyBranchEvaluationMapper.deletePartyBranchEvaluationById(id);
    }

    @Override
    public boolean editPartyBranchEvaluationById(PartyBranchEvaluation partyBranchEvaluation) {
        return partyBranchEvaluationMapper.editPartyBranchEvaluationById(partyBranchEvaluation);
    }
}
