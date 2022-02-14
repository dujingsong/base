package cn.imadc.application.base.auth.jwt;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>
 * 密钥工具
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
public class PemUtils {

    /**
     * 解析密钥文件
     *
     * @param pemFile 密钥文件
     * @return 密钥bute
     * @throws IOException
     */
    private static byte[] parsePEMFile(File pemFile) throws IOException {
        if (!pemFile.isFile() || !pemFile.exists()) {
            throw new FileNotFoundException(String.format("The file '%s' doesn't exist.", pemFile.getAbsolutePath()));
        }
        PemReader reader = new PemReader(new FileReader(pemFile));
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();
        return content;
    }

    /**
     * 解析密钥文件
     *
     * @param pemInputStreamReader 密钥文件
     * @return 密钥byte
     * @throws IOException
     */
    private static byte[] parsePEMFile(InputStreamReader pemInputStreamReader) throws IOException {
        PemReader reader = new PemReader(pemInputStreamReader);
        PemObject pemObject = reader.readPemObject();
        byte[] content = pemObject.getContent();
        reader.close();
        return content;
    }

    /**
     * 获取公钥
     *
     * @param keyBytes  公钥信息
     * @param algorithm 加密算法
     * @return 公钥
     */
    private static PublicKey getPublicKey(byte[] keyBytes, String algorithm) {
        PublicKey publicKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            publicKey = kf.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the public key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the public key");
        }

        return publicKey;
    }

    /**
     * 获取私钥
     *
     * @param keyBytes  私钥信息
     * @param algorithm 加密算法
     * @return 私钥
     */
    private static PrivateKey getPrivateKey(byte[] keyBytes, String algorithm) {
        PrivateKey privateKey = null;
        try {
            KeyFactory kf = KeyFactory.getInstance(algorithm);
            EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Could not reconstruct the private key, the given algorithm could not be found.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Could not reconstruct the private key");
        }

        return privateKey;
    }

    /**
     * 读取公钥文件
     *
     * @param filepath  公钥文件
     * @param algorithm 加密算法
     * @return 公钥文件
     * @throws IOException
     */
    public static PublicKey readPublicKeyFromFile(String filepath, String algorithm) throws IOException {
        Resource resource = new ClassPathResource(filepath);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        byte[] bytes = PemUtils.parsePEMFile(inputStreamReader);
        return PemUtils.getPublicKey(bytes, algorithm);
    }

    /**
     * 读取私钥文件
     *
     * @param filepath  私钥文件
     * @param algorithm 加密算法
     * @return 私钥文件
     * @throws IOException
     */
    public static PrivateKey readPrivateKeyFromFile(String filepath, String algorithm) throws IOException {
        Resource resource = new ClassPathResource(filepath);
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        byte[] bytes = PemUtils.parsePEMFile(inputStreamReader);
        return PemUtils.getPrivateKey(bytes, algorithm);
    }
}