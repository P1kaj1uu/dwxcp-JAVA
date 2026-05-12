package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.Basic;
import com.springboot.dwxcp.mapper.BasicMapper;
import com.springboot.dwxcp.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicServiceImpl extends ServiceImpl<BasicMapper, Basic> implements BasicService {
    @Autowired
    private BasicMapper basicMapper;

    @Override
    public List<Basic> getBasicList(int pageNum, int pageSize) {
        return basicMapper.getBasicList(pageNum, pageSize);
    }

    @Override
    public List<Basic> getBasicByType(String type) {
        return basicMapper.getBasicByType(type);
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
