/*
 * author     : dujun
 * date       : 2022/3/9 17:27
 * description: 告诉大家我是干啥的
 */

package com.dujun.springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dujun.springboot.entity.User;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {

    // 过期时间
    private static final long EXPIRE_TIME = 72 * 60 *60 * 1000;

    // token私钥
    private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e631";

    // 生成token
    public static String sign(User user) {
        try {
//            过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
//            私钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
//            设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "JWT");
            header.put("alg", "HS256");
            // 附带用户信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    //要往token中存储的东西
                    .withClaim("uId", user.getId().toString())
                    .withClaim("account",user.getAccount())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    // 获取token payload 中信息
    public static String getTokenStr(String token, String data) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            if (verify(token)){
                return jwt.getClaim(data).asString();
            }
        } catch (JWTDecodeException e) {
            return null;
        }
        return null;
    }

    // 获取token payload 中信息
    public static Integer getTokenInt(String token, String data) {

        try {
            DecodedJWT jwt = JWT.decode(token);
            if (verify(token)){
                return jwt.getClaim(data).asInt();
            }
        } catch (JWTDecodeException e) {
            return null;
        }
        return null;
    }

    // 获取token payload 中信息
    public static Claim getToken(String token, String data) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            if (verify(token)){
                return jwt.getClaim(data);
            }
        } catch (JWTDecodeException e) {
            return null;
        }
        return null;
    }

    // 校验token 是否正确
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
