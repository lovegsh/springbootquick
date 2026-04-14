package com.gsh.springbootquick.test.encrypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 首先，我们导入了Bouncy Castle库中的相关类。Bouncy Castle是一个开源的加密库，提供了许多加密算法的实现，包括ECC算法。
 * 在main方法中，我们首先生成了一个ECC密钥对。使用KeyPairGenerator类和EC算法生成密钥对，并指定密钥长度为256位。然后，我们获取私钥和公钥。
 * 然后，我们使用IESEngine类进行加密和解密操作。IESEngine类实现了基于椭圆曲线密码学（ECC）的加密和解密算法。在加密过程中，我们使用ElGamalEngine类进行加密操作，并设置相关的参数，包括公钥、随机数等。在解密过程中，我们使用同样的参数进行解密操作。
 * 最后，我们打印加密后的密文和解密后的明文。在打印密文时，我们使用了Hex类将字节数组转换为十六进制字符串。在打印明文时，我们将字节数组转换为字符串。
 */
public class ECC {
    public static void main(String[] args) throws Exception {
        // 生成ECC密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGenerator.initialize(256);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 加密数据
//        byte[] plaintext = "Hello, World!".getBytes();
//        IESEngine iesEngine = new IESEngine(new ElGamalEngine(), new ElGamalEngine());
//        IESParameters iesParameters = new IESParameters(ECPublicKeyParameters.fromPublicKey(publicKey), (ECPoint) null, new SecureRandom());
//        iesEngine.init(true, new ParametersWithRandom(privateKey, new SecureRandom()));
//        byte[] ciphertext = iesEngine.processBlock(plaintext, 0, plaintext.length);
//        System.out.println("Ciphertext: " + Hex.toHexString(ciphertext));
//
//        // 解密数据
//        iesEngine = new IESEngine(new ElGamalEngine(), new ElGamalEngine());
//        iesEngine.init(false, new ParametersWithRandom(publicKey, new SecureRandom()));
//        byte[] decryptedText = iesEngine.processBlock(ciphertext, 0, ciphertext.length);
//        System.out.println("Decrypted Text: " + new String(decryptedText));
    }
}
