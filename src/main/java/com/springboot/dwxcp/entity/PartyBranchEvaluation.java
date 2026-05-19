package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PartyBranchEvaluation {
    private Long id;

    private String year; // 年度

    private String partyBranch; // 党小组

    private String quarter; // 季度

    private String level; // 等级
}
