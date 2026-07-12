package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.EvaluationResult;
import com.springboot.dwxcp.mapper.EvaluationResultMapper;
import com.springboot.dwxcp.service.EvaluationResultService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationResultServiceImpl extends ServiceImpl<EvaluationResultMapper, EvaluationResult> implements EvaluationResultService {
    @Autowired
    private EvaluationResultMapper evaluationResultMapper;

    @Override
    public PageInfo<EvaluationResult> getEvaluationResultList(int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<EvaluationResult> list = evaluationResultMapper.getEvaluationResultList();
        return new PageInfo<>(list);
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
