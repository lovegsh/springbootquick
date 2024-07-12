package com.gsh.springbootquick.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author GSH
 * @create 2023/3/5 16:32
 */
public final class JwtUtil {
    public static final String SECRET = "ADF!@#DF#3";

    private JwtUtil() {
    }

    public static String generateToken(Long userId, String username) {
        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.DAYS))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }

    public static Long getUserId(String token) {
        return JWT.decode(token).getClaim("userId").asLong();
    }

    public static boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException | IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isExpired(String token){
        return JWT.decode(token).getExpiresAt().before(new Date());
    }

}

