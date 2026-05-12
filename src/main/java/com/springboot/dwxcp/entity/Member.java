package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Member {
    private Long id;

    private String name; // 姓名

    private String sex; // 性别

    private String nation; // 民族

    private String culture; // 文化

    private String birthday; // 出生年月

    private String job; // 工作时间

    private String party; // 入党时间

    private String position; // 职务

    private String title; // 职称

    private String address; // 家庭住址

    private String condition; // 困难、老党员

    private String groups; // 所在党小组

    private String transfer; // 调离本车间时间变动

    private String remark; // 备注
}
