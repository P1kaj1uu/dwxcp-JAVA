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
        // 列表查询已带 LIMIT 200，走 mapper 自定义 SQL（不拉 BLOB）
        return mediaMapper.selectMediaListWithoutContent(category);
    }

    @Override
    public Media selectMediaById(Long id) {
        // 走 mapper 自定义 SQL：列表字段 + BLOB
        return mediaMapper.selectMediaById(id);
    }

    /**
     * 仅下载 file_content，避免拉全行
     */
    public byte[] selectMediaContent(Long id) {
        return mediaMapper.selectMediaContent(id);
    }

    @Override
    public boolean deleteMediaById(Long id) {
        return mediaMapper.deleteMediaById(id) > 0;
    }
}