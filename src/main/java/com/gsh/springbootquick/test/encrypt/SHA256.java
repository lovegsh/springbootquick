package com.gsh.springbootquick.test.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    public static void main(String[] args) {
        // 待加密的数据
        String plainText = "Hello, World!";

        // 加密数据
        String encryptedText = encrypt(plainText);
        System.out.println("Encrypted Text: " + encryptedText);
    }

    /**
     * 使用SHA-256算法对数据进行加密
     * @param plainText 待加密的明文数据
     * @return 加密后的密文数据，以Base64格式编码
     */
    public static String encrypt(String plainText) {
        // 创建一个MessageDigest实例，用于计算SHA-256哈希值
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // 计算明文数据的SHA-256哈希值
            byte[] hashBytes = messageDigest.digest(plainText.getBytes());
            // 将哈希值转换为16进制字符串并返回
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将字节数组转换为16进制字符串
     * @param bytes 字节数组
     * @return 16进制字符串表示的字节数组内容
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
