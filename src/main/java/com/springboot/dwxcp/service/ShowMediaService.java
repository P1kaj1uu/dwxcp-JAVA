package com.springboot.dwxcp.service;

import com.springboot.dwxcp.entity.ShowMedia;

import java.util.List;

public interface ShowMediaService {
    // 返回主页多媒体展示图片还是视频
    List<ShowMedia> getShowMediaList();

    // 编辑
    boolean editShowMediaById(ShowMedia showMedia);
}
