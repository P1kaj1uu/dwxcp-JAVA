package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.Branchs;
import com.springboot.dwxcp.mapper.BranchsMapper;
import com.springboot.dwxcp.service.BranchsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchsServiceImpl extends ServiceImpl<BranchsMapper, Branchs> implements BranchsService {
    @Autowired
    private BranchsMapper branchsMapper;

    @Override
    public List<Branchs> getBranchsList(int pageNum, int pageSize) {
        return branchsMapper.getBranchsList(pageNum, pageSize);
    }

    @Override
    public boolean addBranchs(Branchs branchs) {
        return branchsMapper.addBranchs(branchs);
    }

    @Override
    public boolean deleteBranchsById(int id) {
        return branchsMapper.deleteBranchsById(id);
    }

    @Override
    public boolean editBranchsById(Branchs branchs) {
        return branchsMapper.editBranchsById(branchs);
    }

    @Override
    public boolean batchAddBranchs(List<Branchs> branchsList) {
        return branchsMapper.batchAddBranchs(branchsList);
    }
}
