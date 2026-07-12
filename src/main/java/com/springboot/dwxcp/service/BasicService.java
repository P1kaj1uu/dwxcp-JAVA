package com.springboot.dwxcp.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.Basic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicService {
    // 查询基本情况列表（分页）
    PageInfo<Basic> getBasicList(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize
    );

    // 按部门筛选（分页）
    PageInfo<Basic> getBasicByType(@Param("type") String type,
                                   @Param("pageNum") int pageNum,
                                   @Param("pageSize") int pageSize);

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
