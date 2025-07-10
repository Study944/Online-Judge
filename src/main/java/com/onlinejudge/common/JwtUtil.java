package com.onlinejudge.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.onlinejudge.exception.BusinessException;
import com.onlinejudge.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT工具类
 */
@Slf4j
public class JwtUtil {

    // 加密算法
    static Algorithm algorithm = Algorithm.HMAC256("ZOJ");

    /**
     * 生成Token
     * @param userId 用户id
     */
    public static String generateToken(Long userId) {
        // 设置过期时间
        Long exp = System.currentTimeMillis() + 7200000;
        Date expireTime = new Date(exp);
        // 根据用户信息生成Token
        String token = JWT.create()
                .withClaim("userId", userId)
                .withExpiresAt(expireTime)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证Token
     * @param token
     */
    public static boolean verify(String token){
        // 捕获校验Token的常见异常
        try{
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"签名验证失败");
        } catch (TokenExpiredException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"Token过期");
        } catch (AlgorithmMismatchException e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"加密算法不匹配");
        } catch (Exception e) {
            log.error("JWT验证异常", e);
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        return JWT.decode(token).getClaim("userId").asLong();
    }
}
