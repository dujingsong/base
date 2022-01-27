package cn.imadc.application.base.auth.jwt;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * <p>
 * JWT加解密算法
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
public class Algorithm {

    private static com.auth0.jwt.algorithms.Algorithm algorithm;

    // 公钥存放路径
    private static final String PUBLIC_KEY_PATH = "key/rsa_pub.key";
    // 私钥存放路径
    private static final String PRIVATE_KEY_PATH = "key/rsa_pri.key";

    static {
        // 初始化
        init();
    }

    /**
     * 初始化，这里使用的rsa非堆成加密
     */
    private static void init() {
        // RSA
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

    /**
     * 获取密钥算法
     *
     * @return 密钥算法
     */
    public static com.auth0.jwt.algorithms.Algorithm algorithm() {
        return algorithm;
    }
}
