package cn.imadc.application.base.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String token = JWT.create()
                .withIssuer("auth0")
                .withSubject("1")
                .withExpiresAt(new Date("2022-01-24 00:00:00"))
                .sign(Algorithm.algorithm());
        System.out.println(token);
        DecodedJWT jwt = JWT.decode(token);

        System.out.println(jwt.getSubject());

        JWTVerifier verifier = JWT.require(Algorithm.algorithm()).build();
        DecodedJWT jwt1 = verifier.verify(token);

        System.out.println(jwt1);
    }
}
