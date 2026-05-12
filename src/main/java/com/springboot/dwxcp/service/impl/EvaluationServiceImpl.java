package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.Evaluation;
import com.springboot.dwxcp.mapper.EvaluationMapper;
import com.springboot.dwxcp.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public List<Evaluation> getEvaluationList(String name, String responsibilityPost,
                                              String responsibilityArea, int pageNum, int pageSize) {
        return evaluationMapper.getEvaluationList(name, responsibilityPost, responsibilityArea, pageNum, pageSize);
    }

    @Override
    public boolean addEvaluation(Evaluation evaluation) {
        return evaluationMapper.addEvaluation(evaluation);
    }

    @Override
    public boolean deleteEvaluationById(int id) {
        return evaluationMapper.deleteEvaluationById(id);
    }

    @Override
    public boolean editEvaluationById(Evaluation evaluation) {
        return evaluationMapper.editEvaluationById(evaluation);
    }

    @Override
    public boolean batchAddEvaluation(List<Evaluation> evaluationList) {
        return evaluationMapper.batchAddEvaluation(evaluationList);
    }
}
