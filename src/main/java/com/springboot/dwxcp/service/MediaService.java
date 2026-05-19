package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.Media;

import java.util.List;

public interface MediaService {

    boolean uploadMedia(Media media);

    List<Media> selectMediaList(String category);

    Media selectMediaById(Long id);

    boolean deleteMediaById(Long id);
}