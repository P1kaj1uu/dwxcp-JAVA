package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.PartyBranchEvaluation;
import com.springboot.dwxcp.mapper.PartyBranchEvaluationMapper;
import com.springboot.dwxcp.service.PartyBranchEvaluationService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartyBranchEvaluationServiceImpl extends ServiceImpl<PartyBranchEvaluationMapper, PartyBranchEvaluation> implements PartyBranchEvaluationService {
    @Autowired
    private PartyBranchEvaluationMapper partyBranchEvaluationMapper;

    @Override
    public PageInfo<PartyBranchEvaluation> getPartyBranchEvaluationList(String partyBranch, String year, String quarter, int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<PartyBranchEvaluation> list = partyBranchEvaluationMapper.getPartyBranchEvaluationList(partyBranch, year, quarter);
        return new PageInfo<>(list);
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
