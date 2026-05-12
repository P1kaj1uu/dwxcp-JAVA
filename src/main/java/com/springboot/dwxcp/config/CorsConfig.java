package com.springboot.dwxcp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS跨域配置
 * 允许任何域名、端口访问后端接口
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // 允许所有域名（使用 addAllowedOriginPattern 支持动态端口）
        config.addAllowedOriginPattern("*");

        // 允许携带 Cookie 和认证信息
        config.setAllowCredentials(true);

        // 允许所有请求头
        config.addAllowedHeader("*");

        // 允许所有 HTTP 方法
        config.addAllowedMethod("*");

        // 预检请求缓存时间（秒）
        config.setMaxAge(3600L);

        // 对所有路径应用此配置
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
