package com.gsh.springbootquick.test.encrypt;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

// 非对称
public class RSA {
    // 公钥和私钥
    private static final String RSA_PUBLIC_KEY = "RSA PUBLIC KEY";
    private static final String RSA_PRIVATE_KEY = "RSA PRIVATE KEY";

    public static void main(String[] args) throws Exception {
        // 创建密钥对生成器
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // 密钥长度，推荐使用2048位

        // 生成密钥对
        KeyPair pair = keyGen.generateKeyPair();
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        // 待加密的消息
        String message = "Hello, RSA!";
        System.out.println("Original Message: " + message);

        // 使用公钥进行加密
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = encryptCipher.doFinal(message.getBytes());
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        // 使用私钥进行解密
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = decryptCipher.doFinal(encryptedMessage);
        System.out.println("Decrypted Message: " + new String(decryptedMessage));
    }
}

