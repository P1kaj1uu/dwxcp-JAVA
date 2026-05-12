package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;
import com.baomidou.mybatisplus.annotation.TableField;

@Data
@ToString
public class Basic {
    private Long id;

    private String name; // 姓名

    private String position; // 职位

    private String type; // 部门

    @TableField("photo")
    private byte[] photo; // 照片数据
}