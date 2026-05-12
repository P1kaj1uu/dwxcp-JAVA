package com.springboot.dwxcp.util;

import java.util.UUID;
import java.util.Date;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

/**
 * 使用Jwt生成及获取token
 */
public class JWTUtil {

    // 过期时间
    private static long tokenExpiration = 24 * 60 *60 * 1000;
    // 签名秘钥
    private static String tokenSignKey = "123456";

    /**
     * 根据参数生成token
     * @param userId   登录用户ID
     * @param userName 登录名称
     * @return
     */
    public static String createToken(Long userId, String userName) {
        // 使用jwt构建token信息
        String token = Jwts.builder()
                // 生成JWT_ID
                .setId(UUID.randomUUID().toString())
                // 设置标题
                .setSubject("login_test")
                // 设置签发人
                .setIssuer("Issuer")
                // 设置接收人
                .setAudience("Audience")
                // 添加自定义值到生成token信息中
                .claim("userId", userId)
                .claim("userName", userName)
                // 设置生成时间
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                // 使用加密算法加密key
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                // 数据压缩方式
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 根据token字符串得到用户id
     * @param token token字符串
     * @return
     */
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        //解析token字符串
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        //获取token信息体
        Claims claims = claimsJws.getBody();
        //获取用户id
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    /**
     * 根据token字符串得到用户名称
     * @param token token字符串
     * @return
     */
    public static String getUserName(String token) {
        if(StringUtils.isEmpty(token)){
            return "";
        }
        //解析token字符串
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        //获取token信息体
        Claims claims = claimsJws.getBody();
        return (String)claims.get("userName");
    }

    /**
     * 检查Token是否过期
     * @param token
     * @return false 未过期，true 已过期
     */
    public static Boolean tokenExpired(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            Date currentDate = new Date();
            return expiration.before(currentDate);
        } catch (ExpiredJwtException ex) {
            return true;
        } catch (Exception ex) {
            return true;
        }
    }
}
