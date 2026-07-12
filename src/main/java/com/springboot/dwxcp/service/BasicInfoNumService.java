package com.springboot.dwxcp.service;

import com.github.pagehelper.PageInfo;
import com.springboot.dwxcp.entity.BasicInfoNum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicInfoNumService {
    // 查询基本信息人员年龄等情况（分页）
    PageInfo<BasicInfoNum> getBasicInfoNumList(@Param("pageNum") int pageNum,
                                               @Param("pageSize") int pageSize);

    // 新增
    boolean addBasicInfoNum(BasicInfoNum basicInfoNum);

    // 编辑
    boolean editBasicInfoNumById(BasicInfoNum basicInfoNum);
}
