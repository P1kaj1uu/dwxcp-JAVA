package com.springboot.dwxcp.config;

import com.springboot.dwxcp.handle.TokenHandle;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description 配置拦截器
 */
@Configuration
public class TokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenHandle())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/api/user/login",
                        "/api/user/add",
                        "/api/email/code",
                        "/api/user/editPassword",
                        "/api/pdf/preview",
                        "/api/basic/upload-photo",
                        "/api/result/add",
                        "/swagger-ui.html",
                        "/webjars/",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/doc.html",
                        "/error"
                );
    }
}
