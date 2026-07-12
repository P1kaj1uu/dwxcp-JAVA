package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.Evaluation;
import com.springboot.dwxcp.mapper.EvaluationMapper;
import com.springboot.dwxcp.service.EvaluationService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl extends ServiceImpl<EvaluationMapper, Evaluation> implements EvaluationService {
    @Autowired
    private EvaluationMapper evaluationMapper;

    @Override
    public PageInfo<Evaluation> getEvaluationList(String name, String year, String quarter, String responsibilityPost,
                                                  String responsibilityArea, int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<Evaluation> list = evaluationMapper.getEvaluationList(name, year, quarter, responsibilityPost, responsibilityArea);
        return new PageInfo<>(list);
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
