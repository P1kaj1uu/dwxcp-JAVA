package com.springboot.dwxcp.util;

import java.util.Random;

/**
 * 邮箱验证码生成工具类
 */
public class VerifyCodeUtils {

    /**
     * 生成6位数字验证码
     */
    public static String generate4DigitCode() {
        Random random = new Random();
        // 生成100000-999999之间的6位随机数
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
