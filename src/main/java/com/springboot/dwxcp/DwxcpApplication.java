package com.springboot.dwxcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan("com.springboot.dwxcp.mapper")
@SpringBootApplication
public class DwxcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DwxcpApplication.class, args);
    }

}
