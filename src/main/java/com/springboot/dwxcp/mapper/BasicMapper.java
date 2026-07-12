package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Basic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicMapper extends BaseMapper<Basic> {
    List<Basic> getBasicList();

    List<Basic> getBasicByType(String type);

    boolean addBasic(Basic basic);

    boolean deleteBasicById(int id);

    boolean editBasicById(Basic basic);

    boolean uploadPhoto(Basic basic);

    Basic selectBasicById(Long id);
}
