package com.tencent.wework;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAPks8Utils {

    // 用此方法先获取秘钥
    public static String getEncryptKey(String privKey, String encryptRandomKey) throws Exception {

        String privKeyPEMnew = privKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");

        byte[] decoded = Base64.getDecoder().decode(privKeyPEMnew);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(encryptRandomKey);

        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
}