package com.gsh.springbootquick.test.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// 对称
public class AES {
    // 定义加密和解密的密钥
    private static final String SECRET_KEY = "mySecretKey";

    public static void main(String[] args) throws Exception {
        // 待加密的数据
        String plainText = "Hello, World!";

        // 加密数据
        String encryptedText = encrypt(plainText);
        System.out.println("Encrypted Text: " + encryptedText);

        // 解密数据
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    /**
     * 加密数据
     * @param plainText 待加密的明文数据
     * @return 加密后的密文数据，以Base64格式编码
     * @throws Exception 加密过程中出现异常时抛出异常
     */
    public static String encrypt(String plainText) throws Exception {
        // 创建AES密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        // 创建Cipher实例并初始化用于加密的算法模式和密钥
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // 加密数据并返回Base64编码的密文数据
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * 解密数据
     * @param encryptedText 待解密的密文数据，以Base64格式编码
     * @return 解密后的明文数据
     * @throws Exception 解密过程中出现异常时抛出异常
     */
    public static String decrypt(String encryptedText) throws Exception {
        // 解码密文数据为字节数组
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

        // 创建AES密钥
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");

        // 创建Cipher实例并初始化用于解密的算法模式和密钥
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // 解密数据并返回明文数据
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}

