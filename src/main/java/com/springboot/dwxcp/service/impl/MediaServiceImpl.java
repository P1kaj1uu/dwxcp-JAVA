package com.springboot.dwxcp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.dwxcp.entity.Media;
import com.springboot.dwxcp.mapper.MediaMapper;
import com.springboot.dwxcp.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaServiceImpl extends ServiceImpl<MediaMapper, Media> implements MediaService {
    @Autowired
    private MediaMapper mediaMapper;

    @Override
    public boolean uploadMedia(Media media) {
        return mediaMapper.insert(media) > 0;
    }

    @Override
    public List<Media> selectMediaList(String category) {
        return mediaMapper.selectMediaListWithoutContent(category);
    }

    @Override
    public Media selectMediaById(Long id) {
        return mediaMapper.selectById(id);
    }

    @Override
    public boolean deleteMediaById(Long id) {
        return mediaMapper.deleteById(id) > 0;
    }
}