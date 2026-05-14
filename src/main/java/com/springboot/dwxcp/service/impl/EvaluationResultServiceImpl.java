package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.EvaluationResult;
import com.springboot.dwxcp.mapper.EvaluationResultMapper;
import com.springboot.dwxcp.service.EvaluationResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationResultServiceImpl extends ServiceImpl<EvaluationResultMapper, EvaluationResult> implements EvaluationResultService {
    @Autowired
    private EvaluationResultMapper evaluationResultMapper;

    @Override
    public List<EvaluationResult> getEvaluationResultList(int pageNum, int pageSize) {
        return evaluationResultMapper.getEvaluationResultList(pageNum, pageSize);
    }

    @Override
    public boolean addEvaluationResult(EvaluationResult evaluationResult) {
        return evaluationResultMapper.addEvaluationResult(evaluationResult);
    }

    @Override
    public boolean editEvaluationResultById(EvaluationResult evaluationResult) {
        return evaluationResultMapper.editEvaluationResultById(evaluationResult);
    }
}
