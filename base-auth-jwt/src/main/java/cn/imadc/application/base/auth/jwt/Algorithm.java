package cn.imadc.application.base.auth.jwt;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Algorithm {

    private static com.auth0.jwt.algorithms.Algorithm algorithm;

    private static final String PUBLIC_KEY_PATH = "classpath:key/rsa_pub.key";
    private static final String PRIVATE_KEY_PATH = "classpath:key/rsa_pri.key";

    static {
        init();
    }

    private static void init() {
        //RSA
        RSAPublicKey publicKey = null;
        try {
            publicKey = (RSAPublicKey) PemUtils.readPublicKeyFromFile(PUBLIC_KEY_PATH, "RSA");
        } catch (IOException e) {
            e.printStackTrace();
        }
        RSAPrivateKey privateKey = null;
        try {
            privateKey = (RSAPrivateKey) PemUtils.readPrivateKeyFromFile(PRIVATE_KEY_PATH, "RSA");
        } catch (IOException e) {
            e.printStackTrace();
        }
        algorithm = com.auth0.jwt.algorithms.Algorithm.RSA256(publicKey, privateKey);
    }

    public static com.auth0.jwt.algorithms.Algorithm algorithm() {
        return algorithm;
    }
}
