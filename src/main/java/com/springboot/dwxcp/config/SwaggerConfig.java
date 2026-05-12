package com.springboot.dwxcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Description 配置Swagger
 * 访问地址：http://127.0.0.1:2645/swagger-ui/index.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // RequestHandlerSelectors配置扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("com.springboot.dwxcp.controller"))
                // path过滤什么路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "智慧教育系统",
                "智慧教育系统的API接口文档",
                "v1.0",
                "http:127.0.0.1/",
                new Contact("开发团队", "", ""),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
