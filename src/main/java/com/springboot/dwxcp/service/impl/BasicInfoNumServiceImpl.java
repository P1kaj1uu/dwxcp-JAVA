package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.BasicInfoNum;
import com.springboot.dwxcp.mapper.BasicInfoNumMapper;
import com.springboot.dwxcp.service.BasicInfoNumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInfoNumServiceImpl extends ServiceImpl<BasicInfoNumMapper, BasicInfoNum> implements BasicInfoNumService {
    @Autowired
    private BasicInfoNumMapper basicInfoNumMapper;


    @Override
    public List<BasicInfoNum> getBasicInfoNumList(int pageNum, int pageSize) {
        return basicInfoNumMapper.getBasicInfoNumList(pageNum, pageSize);
    }

    @Override
    public boolean addBasicInfoNum(BasicInfoNum basicInfoNum) {
        return basicInfoNumMapper.addBasicInfoNum(basicInfoNum);
    }

    @Override
    public boolean editBasicInfoNumById(BasicInfoNum basicInfoNum) {
        return basicInfoNumMapper.editBasicInfoNumById(basicInfoNum);
    }
}
