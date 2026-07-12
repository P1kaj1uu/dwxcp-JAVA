package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.Basic;
import com.springboot.dwxcp.mapper.BasicMapper;
import com.springboot.dwxcp.service.BasicService;
import com.springboot.dwxcp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicServiceImpl extends ServiceImpl<BasicMapper, Basic> implements BasicService {
    @Autowired
    private BasicMapper basicMapper;

    @Override
    public PageInfo<Basic> getBasicList(int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<Basic> list = basicMapper.getBasicList();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Basic> getBasicByType(String type, int pageNum, int pageSize) {
        PageUtil.PageParams p = PageUtil.guard(pageNum, pageSize);
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<Basic> list = basicMapper.getBasicByType(type);
        return new PageInfo<>(list);
    }

    @Override
    public boolean addBasic(Basic basic) {
        return basicMapper.addBasic(basic);
    }

    @Override
    public boolean deleteBasicById(int id) {
        return basicMapper.deleteBasicById(id);
    }

    @Override
    public boolean editBasicById(Basic basic) {
        return basicMapper.editBasicById(basic);
    }

    @Override
    public boolean uploadPhoto(Basic basic) {
        return basicMapper.uploadPhoto(basic);
    }

    @Override
    public Basic selectBasicById(Long id) {
        return basicMapper.selectBasicById(id);
    }
}
