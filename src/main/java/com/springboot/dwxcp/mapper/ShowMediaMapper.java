package com.springboot.dwxcp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.dwxcp.entity.ShowMedia;

import java.util.List;

public interface ShowMediaMapper extends BaseMapper<ShowMedia> {
    List<ShowMedia> getShowMediaList();

    boolean editShowMediaById(ShowMedia showMedia);
}
