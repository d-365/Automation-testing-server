/**
 * author     : dujun
 * date       : 2022/8/29 10:39
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

public class JwtsUtils {

    //有效期为
    public static final Long JWT_TTL = 72* 60 * 60 *1000L; // 60 * 60 *1000  三天

    //设置秘钥明文
    public static final String JWT_KEY = "dujun1";


    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, null);// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, null);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis= JwtsUtils.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, JWT_KEY) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }


    /**
     * 解析

     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(JWT_KEY)
                .parseClaimsJws(JWT_KEY)
                .getBody();
    }

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1MTM3ZjYzNjRmOGU0YWJkYTU2ODc3MGViYzJmOGU2YiIsInN1YiI6ImR1anVuIiwiaXNzIjoic2ciLCJpYXQiOjE2NjE3NDE2MjksImV4cCI6MTY2MjAwMDgyOX0.oP11ItD4DdxB-xcXTGpxA3klBBUiMwzPM47IJn8kNq4\n";
        String data = "dujun";
        System.out.println(JwtsUtils.parseJWT(token));
    }

}
