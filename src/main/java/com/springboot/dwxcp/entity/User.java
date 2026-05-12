package com.springboot.dwxcp.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    // 用户id
    private Long id;

    // 用户名
    private String username;

    // 用户密码
    private String password;
}
