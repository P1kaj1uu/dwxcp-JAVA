package com.springboot.dwxcp.handle;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class TokenHandle implements HandlerInterceptor {

    // 定义不需要token验证的路径
    private static final List<String> EXCLUDE_PATHS = Arrays.asList(
            "/api/user/login",
            "/api/user/register",
            "/swagger-ui.html",
            "/webjars/",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/doc.html",
            "/error"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 调试信息
        System.out.println("拦截器执行，请求路径: " + requestURI);

        // 检查是否在排除路径中
        if (isExcludePath(requestURI)) {
            System.out.println("路径被排除，无需验证token: " + requestURI);
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        System.out.println("获取到的token: " + token);

        // 校验token
        if (StringUtils.isEmpty(token)) {
            // 未登录
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            try {
                JSONObject json = new JSONObject();
                json.put("code", 401);
                json.put("msg", "Token失效");
                json.put("path", requestURI);  // 添加路径信息方便调试
                out = response.getWriter();
                out.append(json.toString());
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(500);
                return false;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
        }
        return true;
    }

    /**
     * 判断请求路径是否在排除列表中
     */
    private boolean isExcludePath(String requestURI) {
        for (String excludePath : EXCLUDE_PATHS) {
            if (excludePath.endsWith("/**") || excludePath.endsWith("/*")) {
                // 处理通配符路径
                String prefix = excludePath.substring(0, excludePath.length() - 3);
                if (requestURI.startsWith(prefix)) {
                    return true;
                }
            } else if (excludePath.endsWith("/")) {
                // 处理目录路径
                if (requestURI.startsWith(excludePath)) {
                    return true;
                }
            } else {
                // 精确匹配
                if (requestURI.equals(excludePath)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
