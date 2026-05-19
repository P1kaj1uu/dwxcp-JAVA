package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ShowMedia {
    private Long id;

    private String type; // 主页多媒体展示图片或视频
}
