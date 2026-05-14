package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EvaluationResult {
    private Long id;

    private String one; // 一季度

    private String two; // 二季度

    private String three; // 三季度

    private String four; // 四季度

    private String years; // 上年度
}
