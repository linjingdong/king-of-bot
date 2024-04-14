package com.lin.kob.backend.consumer.utils;

import com.lin.kob.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;

/**
 * @Description TODO
 * @Date 2024/4/14 14:44
 * @Author Lin
 * @Version 1.0
 */
public class JwtAuthentication {
    public static Integer getUserId(String token) {
        int userId = -1;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}
