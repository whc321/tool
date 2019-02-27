package com.whc.util;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;

/**
 * Created by wang_haichun on 2017/8/10.
 */
public class AESUtil {
    private static Logger logger = LoggerFactory.getLogger(AESUtil.class);

    /**
     * AES加密
     * @param password
     * @param content
     * @param encryptType
     * @param block
     * @param tokenizer
     * @param encoding
     * @param iv
     * @return
     */
    public static String encrypt(String password,String content,String encryptType,Integer block,String tokenizer,String encoding,String iv,String returnType) {
        try {
            byte[] raw = password.getBytes(encoding);
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance(encryptType.toUpperCase());
            // 利用用户密码作为随机数初始化出
            kgen.init(block, new SecureRandom(raw));
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(raw, encryptType.toUpperCase());

            /**
             * 这个地方调用BouncyCastleProvider
             * 让java支持补码方式
             */
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            // 创建密码器
            Cipher cipher = Cipher.getInstance(tokenizer);
            byte[] byteContent = content.getBytes(encoding);

            // 初始化为加密模式的密码器
            if(StringUtils.isEmpty(iv)){
                cipher.init(Cipher.ENCRYPT_MODE, key);
            }else {
                //使用CBC模式，需要一个向量iv，可增加加密算法的强度
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(encoding));
                cipher.init(Cipher.ENCRYPT_MODE, key,ivParameterSpec);
            }
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            if("1".equals(returnType)){
                return AESUtil.parseByte2HexStr(result);
            }else {
                return Base64.encodeBase64String(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    /**
     * AES解密
     * @param password
     * @param content
     * @return
     */
    public static String decrypt(String password,String content,String encryptType,Integer block,String tokenizer,String encoding,String iv,String returnType) {
        try {
            byte[] raw = password.getBytes("utf-8");
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance(encryptType.toUpperCase());
            kgen.init(block, new SecureRandom(raw));
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(raw, encryptType.toUpperCase());
            // 创建密码器
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance(tokenizer);
            byte[] byteContent = Base64.decodeBase64(content);
            // 初始化为解密模式的密码器
            if(StringUtils.isEmpty(iv)){
                cipher.init(Cipher.DECRYPT_MODE, key);
            }else {
                //使用CBC模式，需要一个向量iv，可增加加密算法的强度
                IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(encoding));
                cipher.init(Cipher.DECRYPT_MODE, key,ivParameterSpec);
            }
            byte[] result = cipher.doFinal(byteContent);
            // 明文
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }



    /**
     * AES加密
     * @param password
     * @param content
     * @return
     */
    public static String encrypt(String password,String content) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            // 利用用户密码作为随机数初始化出
            kgen.init(128, new SecureRandom(password.getBytes()));
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            return AESUtil.parseByte2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * AES解密
     * @param password
     * @param content
     * @return
     */
    public static String decrypt(String password,byte[] content) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            // 根据用户密码，生成一个密钥
            SecretKey secretKey = kgen.generateKey();
            // 返回基本编码格式的密钥
            byte[] enCodeFormat = secretKey.getEncoded();
            // 转换为AES专用密钥
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            // 初始化为解密模式的密码器
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            // 明文
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;
        }
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }





    /*public static void main(String[] args) {
        String a = "cWMzZVFJM05HQjF3MkJtRWk0czdqRXZTa2xUeXNqb1N0aVp3Y2NyL0NDdXpQeDd5UWtISEs1dGh1NkNOY2NoMndUL0xuWnpkMFRYZG1ZaWtSdFJWK0pHMTN2N21OVG1VT0oyc0J5blhlU0RGVDU4YTFxS3AzOC9GQ2dxTXo0SlBTdWROL3laT1llajBKdjJzUElNY0pFcm5UZjhtVG1IbzlDYjlyRHlESENRZXE4a2pvdTVYNmZFd0pIcmorMms3";
        try {
            String orderBase64 = new String(Base64.decodeBase64(a),"utf-8");
            String orderDncode = SecurityUtil.decryptAes(orderBase64, "xqsjT9yzcY0AQhjl");
            System.out.println(orderDncode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

}
