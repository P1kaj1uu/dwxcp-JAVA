package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.BasicInfoNum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasicInfoNumMapper extends BaseMapper<BasicInfoNum> {
    List<BasicInfoNum> getBasicInfoNumList(@Param("pageNum") int pageNum,
                                                   @Param("pageSize") int pageSize);

    boolean addBasicInfoNum(BasicInfoNum basicInfoNum);

    boolean editBasicInfoNumById(BasicInfoNum basicInfoNum);
}
