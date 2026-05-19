package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.ShowMedia;
import com.springboot.dwxcp.mapper.ShowMediaMapper;
import com.springboot.dwxcp.service.ShowMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowMediaServiceImpl extends ServiceImpl<ShowMediaMapper, ShowMedia> implements ShowMediaService {
    @Autowired
    private ShowMediaMapper showMediaMapper;

    @Override
    public List<ShowMedia> getShowMediaList() {
        return showMediaMapper.getShowMediaList();
    }

    @Override
    public boolean editShowMediaById(ShowMedia showMedia) {
        return showMediaMapper.editShowMediaById(showMedia);
    }
}
