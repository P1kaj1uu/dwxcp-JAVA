package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Branchs {
    private Long id;

    private String count1; // 党小组数

    private String count2; // 班组数

    private String count3; // 在职职工数

    private String count4; // 在岗职工数

    private String count5; // 团员人数

    private String count6; // 入党申请人数

    private String count7; // 入党积极分子数

    private String count8; // 发展对象数

    private String count9; // 预备党员数

    private String count10; // 正式党员数

    private String count11; // 大学及以上

    private String count12; // 大专

    private String count13; // 中专中技

    private String count14; // 高中

    private String count15; // 初中及以下

    private String count16; // 高级技师数

    private String count17; // 技师数

    private String count18; // 高级工数

    private String count19; // 中级工数

    private String count20; // 初级工数

    private String time; // 更新时间
}
