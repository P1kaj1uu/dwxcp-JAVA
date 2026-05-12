package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Groups {
    private Long id;

    private String party; // 党小组名称

    private String name; // 党小组长

    private String fushuji; // 副书记

    private String zuzhi; // 组织委员

    private String xuanchuan; // 宣传委员

    private String counts; // 党员数

    private String jobs; // 职工数

    private String name1; // 覆盖班组名称

    private String name2; // 无党员班组名称
}
