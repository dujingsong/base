package cn.imadc.application.base.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * JWT
 */
public class JWT {

    /**
     * 生成token
     *
     * @param subject  主体标识
     * @param expireAt 过期于（时间戳）
     * @return token
     */
    public static String generate(String subject, long expireAt) {
        return com.auth0.jwt.JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(expireAt))
                .sign(Algorithm.algorithm());
    }

    /**
     * 获取主体信息
     *
     * @param token token
     * @return 主体信息
     */
    public static String subject(String token) {
        JWTVerifier verifier = com.auth0.jwt.JWT.require(Algorithm.algorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }
}
