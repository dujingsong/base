package cn.imadc.application.base.auth.jwt;

import cn.imadc.application.base.common.action.IEnumAble;
import cn.imadc.application.base.common.enums.AuthType;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * JWT逻辑
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
public class JWT {

    // ---------------------------------------------生成token---------------------------------------------

    /**
     * 生成token
     *
     * @param issuer   主体类型
     * @param subject  主体标识
     * @param expireAt 过期于（时间戳）
     * @return token
     */
    private static String generate(IEnumAble issuer, String subject, Long expireAt) {
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
        builder.withSubject(subject);
        if (null != expireAt) {
            builder.withExpiresAt(new Date(expireAt));
        }
        builder.withIssuer(issuer.v());
        builder.withIssuedAt(new Date());
        return builder.sign(Algorithm.algorithm());
    }

    /**
     * 生成token
     *
     * @param issuer  主体类型
     * @param subject 主体标识
     * @return token
     */
    public static String generate(IEnumAble issuer, String subject) {
        return generate(issuer, subject, null);
    }

    /**
     * 生成token
     *
     * @param issuer  主体类型
     * @param subject 主体标识
     * @return token
     */
    public static String generate(IEnumAble issuer, Long subject) {
        return generate(issuer, subject.toString(), null);
    }

    /**
     * 生成token
     *
     * @param issuer         主体类型
     * @param subject        主体标识
     * @param expireTime     过期时间
     * @param expireTimeUnit 过期时间单位
     * @return token
     */
    public static String generate(IEnumAble issuer, String subject, long expireTime, TimeUnit expireTimeUnit) {
        long expireAt = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(expireTime, expireTimeUnit);
        return generate(issuer, subject, expireAt);
    }

    /**
     * 生成token
     *
     * @param issuer         主体类型
     * @param subject        主体标识
     * @param expireTime     过期时间
     * @param expireTimeUnit 过期时间单位
     * @return token
     */
    public static String generate(IEnumAble issuer, Long subject, long expireTime, TimeUnit expireTimeUnit) {
        long expireAt = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(expireTime, expireTimeUnit);
        return generate(issuer, subject.toString(), expireAt);
    }

    // ---------------------------------------------解析token---------------------------------------------

    /**
     * 解析token
     *
     * @param token token
     * @return 解析过后的token体
     * @throws JWTDecodeException 非法token会抛出此异常
     */
    public static DecodedJWT decodedJWT(String token) throws JWTDecodeException {
        return com.auth0.jwt.JWT.decode(token);
    }

    /**
     * 获取主体信息
     *
     * @param token token
     * @return 主体信息
     * @throws JWTVerificationException token校验失败会抛出此异常
     */
    public static String subject(String token) throws JWTVerificationException {
        JWTVerifier verifier = com.auth0.jwt.JWT.require(Algorithm.algorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    /**
     * 获取主体类型信息
     *
     * @param token token
     * @return 主体类型信息
     * @throws JWTVerificationException token校验失败会抛出此异常
     */
    public static String issuer(String token) throws JWTVerificationException {
        JWTVerifier verifier = com.auth0.jwt.JWT.require(Algorithm.algorithm()).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getIssuer();
    }

    public static void main(String[] args) {
        System.out.println(issuer(generate(AuthType.AUTHORIZED, 1L, 5, TimeUnit.SECONDS)));
    }
}
