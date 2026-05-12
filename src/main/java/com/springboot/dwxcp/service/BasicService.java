package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Basic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicService {
    // 查询基本情况列表
    List<Basic> getBasicList(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 按部门筛选
    List<Basic> getBasicByType(String type);

    // 新增
    boolean addBasic(Basic basic);

    // 删除
    boolean deleteBasicById(int id);

    // 编辑
    boolean editBasicById(Basic basic);

    // 上传照片
    boolean uploadPhoto(Basic basic);

    // 根据ID查询
    Basic selectBasicById(Long id);
}
