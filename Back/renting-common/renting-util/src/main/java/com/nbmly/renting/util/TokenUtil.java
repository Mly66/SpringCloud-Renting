package com.nbmly.renting.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;

import java.nio.charset.StandardCharsets;

public class TokenUtil {
    private final static String TOKEN_CODE = "renting666";

    /**
     * 解析token
     * 
     * @param jwtToken 令牌
     * @return 用户信息
     */
    public static String chekaToken(String jwtToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_CODE)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(jwtToken);
        } catch (Exception e) {
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        String user_name = null;
        try {
            user_name = Jwts.parser().setSigningKey(TOKEN_CODE.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwtToken).getBody().get("user_name", String.class);
            // accountDTO = JSON.parseObject(user_name, AccountDTO.class);
        } catch (Exception e) {
            return null;
        }
        return user_name;
    }

    /**
     * 处理token头信息
     * 
     * @param authorization 头信息
     * @return 令牌
     */
    public static String workToken(String authorization) {
        return authorization.split(" ")[1].trim();
    }
}
