package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.Pdf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PdfMapper extends BaseMapper<Pdf> {
    List<Pdf> selectPdfListWithoutContent(@Param("type") String type);

    Pdf selectPdfById(@Param("id") Long id);

    int deletePdfById(@Param("id") Long id);
}
