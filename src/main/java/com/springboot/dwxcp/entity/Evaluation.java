package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Evaluation {
    private Long id;

    private String year; // 年度

    private String quarter; // 季度

    private String partyBranch; // 党支部/党小组

    private String name; // 姓名

    private String responsibilityPost; // 责任岗格次

    private String responsibilityArea; // 责任区岗格次

    private String comments; // 对党员的点评意见

    private String good; // 四优
}
