package com.xiaouchina.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static String buildToken(long expMillis, Map<String, Object> body, String secretKey) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(body)
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey);

        if (expMillis >= 0) {
            long exp = nowMillis + expMillis;
            jwtBuilder.setExpiration(new Date(exp));
        }

        return jwtBuilder.compact();

    }

    public static Map<String, Object> parseToken(String token, String secretKey) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return (Map<String, Object>) claims;
    }

    public static boolean verifyToken(String token, String secretKey) {
        if(token==null || "".equals(token)){
            return false;
        }
        String sign = token.split("\\.")[2];

        if(sign==null || "".equals(sign)){
            return false;
        }
        String tempToken = null;
        try {
            tempToken = Jwts.builder()
                    .setClaims(parseToken(token, secretKey))
                    .signWith(signatureAlgorithm, secretKey).compact();
        } catch (Exception e) {
            return false;
        }
        if (sign.equals(tempToken.split("\\.")[2])) {
            return true;
        }
        return false;
    }
}
