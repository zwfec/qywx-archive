## 配置私钥:
密钥长度： 2048 bit
密钥格式： PKCS#8

com/tencent/wework/Config.java

## 执行方式
```shell
./run.sh
```
运行结果:
```
{"msgid":"9175444330025320568_1616827176_external","action":"send","from":"wmPJn2DgAAIK2pNG","tolist":["xxx"],"roomid":"","msgtime":1616827175919,"msgtype":"text","text":{"content":"测试 一次多条信息 和最新私钥"}}
{"msgid":"2996174893905537049_1616827328_external","action":"send","from":"xxx","tolist":["wmPJn2DgAAIK2pNG"],"roomid":"","msgtime":1616827328894,"msgtype":"text","text":{"content":"嗯嗯，我们越来越好"}} 
```

## 常见错误:

## 错误1:请不要在mac下执行使用:
不支持在mac下执行使用, 无法加载 libWeWorkFinanceSdk_Java.so.


## 错误2: 
Exception in thread "main" java.lang.UnsatisfiedLinkError: no libWeWorkFinanceSdk_Java.so in java.library.path: /Users/xxx/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
at java.base/java.lang.ClassLoader.loadLibrary(ClassLoader.java:2447)
at java.base/java.lang.Runtime.loadLibrary0(Runtime.java:809)
at java.base/java.lang.System.loadLibrary(System.java:1893)
at sdkdemo.main(sdkdemo.java:25)

centos7 解决方法: 

```shell
cp libWeWorkFinanceSdk_Java.so /usr/lib64/
chmod 755 /usr/lib64/libWeWorkFinanceSdk_Java.so
```

## 密钥请使用
密钥长度： 2048 bit
密钥格式： PKCS#8

如果使用的是 PKCS#1 (RSAPks1Utils) 可使用以下 代替: RSAPks8Utils 
```java
package com.tencent.wework;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class RSAPks1Utils {

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
```


