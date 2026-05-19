package com.springboot.dwxcp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("media")
public class Media {
    @TableId(type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    @TableField("file_name")
    private String fileName; // 文件名

    @TableField("file_type")
    private String fileType; // 文件类型

    @TableField("category")
    private String category; // 分类（orgLife组织生活/activityStyle活动风采）

    @TableField("file_url")
    private String fileUrl;

    @TableField("file_content")
    private byte[] fileContent;
}