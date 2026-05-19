package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Media;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MediaMapper extends BaseMapper<Media> {

    List<Media> selectMediaListWithoutContent(@Param("category") String category);

    Media selectMediaById(@Param("id") Long id);

    int deleteMediaById(@Param("id") Long id);
}