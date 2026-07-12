package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.BasicInfoNum;
import com.springboot.dwxcp.mapper.BasicInfoNumMapper;
import com.springboot.dwxcp.service.BasicInfoNumService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicInfoNumServiceImpl extends ServiceImpl<BasicInfoNumMapper, BasicInfoNum> implements BasicInfoNumService {
    @Autowired
    private BasicInfoNumMapper basicInfoNumMapper;


    @Override
    public PageInfo<BasicInfoNum> getBasicInfoNumList(int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<BasicInfoNum> list = basicInfoNumMapper.getBasicInfoNumList();
        return new PageInfo<>(list);
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
