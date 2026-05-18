package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasicInfoNum {
    private Long id;

    private String partyNum1; // 现有党员X名

    private String partyNum2; // 预备党员X名

    private String partyNum3; // 平均年龄X岁

    private String partyNum4; // 现有发展党员X名

    private String partyNum5; // 入党积极分子X名

    private String partyNum6; // 递交入党申请书X名

    private String cheNum1; // 现有班组xx个

    private String cheNum2; // 分会会员xx名

    private String tuanNum1; // 现有团员xx名

    private String tuanNum2; // 预备团委xx名

    private String tuanNum3; // 平均年龄xx岁

    private String tuanNum4; // 现有青工（35岁及以下）xx名
}
