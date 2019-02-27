package com.whc.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.UUID;

public class SecurityUtil {

    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    private static Cipher       cipher         = null;

    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        try {
            cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } //"算法/模式/补码方式"
    }


    public static String bytes2String(byte[] bytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%2X", b));
        }
        return sb.toString();
    }


    /**
     * 加密
     *
     * @param plainText
     *            明文
     * @param keyt
     *            密钥
     * @return 密文
     */
    public static byte[] encryptAES(String plainText, String keyt) {

        try {
            byte[] raw = keyt.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            synchronized (cipher) {
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
                byte[] result = cipher.doFinal(plainText.getBytes("utf-8"));
                return result; // 加密
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获得加密后的密文
     *
     * @param plainText
     * @param keyt
     * @return
     */
    public static String getCipherText(String plainText, String keyt) {

        return SecurityUtil.bytes2String(SecurityUtil.encryptAES(plainText, keyt));
    }


    /**
     * 加密函数。
     *
     * 与{@link #encrypt(byte[], byte[])}不同的是，参数均为字符串
     * 并且在处理时，按照系统平台编码进行处理。
     *
     * @param plaintext 原始明文
     * @param secretKey 密钥字符
     * @return 密文16进制字符串
     *
     * @see #encrypt(byte[], byte[])
     */
    public static String encrypt(String plaintext, String secretKey) {

        String ciphertext = null;
        try {
            byte[] plainBytes = plaintext.getBytes("utf-8");
            byte[] keyBytes = secretKey.getBytes("US-ASCII");
            byte[] cipherBytes = encrypt(plainBytes, keyBytes);
            ciphertext = ByteUtil.toHex(cipherBytes);
        } catch (UnsupportedEncodingException e) {
            //这里不可能抛出UnsupportedEncodingException异常
            e.printStackTrace();
        }
        return ciphertext;
    }


    /**
     * 另一个加密函数。
     *
     * 与{@link #encrypt(String, String)}不同的是，参数均为byte数组。
     *
     * @param plaintext 原始明文
     * @param secretKey 密钥字符
     * @return 密文16进制字符串
     *
     * @see #encrypt(byte[], byte[])
     */
    public static byte[] encrypt(byte[] plainBytes, byte[] keyBytes) {

        return process(plainBytes, keyBytes, Cipher.ENCRYPT_MODE);
    }


    /**
     * 解密函数。
     *
     * 与{@link #decrypt(byte[], byte[])}不同的是，参数均为字符串
     * 并且在处理时，按照系统平台编码进行处理。
     *
     * @param ciphertext 密文字符串
     * @param secretKey 密钥字符
     * @return 成功返回原始明文，出错返回null
     *
     * @see #encrypt(byte[], byte[])
     */
    public static String decrypt(String ciphertext, String secretKey) {

        String plaintext = null;
        try {
            byte[] cipherBytes = ByteUtil.toBytes(ciphertext);
            byte[] keyBytes = secretKey.getBytes();
            byte[] plainBytes = decrypt(cipherBytes, keyBytes);
            plaintext = new String(plainBytes);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return plaintext;
    }


    /**
     * 另一个解密函数。
     *
     * 与{@link #decrypt(String, String)}不同的是，参数均为byte数组。
     *
     * @param cipherBytes 密文字节数组
     * @param secretKey 密钥字节数组
     * @return 成功返回原始字节数组，出错返回null
     *
     * @see #encrypt(byte[], byte[])
     */
    public static byte[] decrypt(byte[] cipherBytes, byte[] keyBytes) {

        return process(cipherBytes, keyBytes, Cipher.DECRYPT_MODE);
    }


    private static byte[] process(byte[] data, byte[] keyBytes, int mode) {

        Key key = new SecretKeySpec(keyBytes, "AES");
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            AlgorithmParameterSpec parmas = new IvParameterSpec(keyBytes);
            cipher.init(mode, key, parmas);
            result = cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 获得UUID
     * @return
     */
    public static String getUUID() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    // BASE64 加密
    @SuppressWarnings("restriction")
    public static String getBase64(String str) {

        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = new sun.misc.BASE64Encoder().encode(b);
        }
        return s;
    }


    // BASE64 解密
    @SuppressWarnings("restriction")
    public static String getFromBase64(String s) {

        byte[] b = null;
        String result = null;
        if (s != null) {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 对字符串加密,使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     */
    public static String SHA256Encrypt(String strSrc) {

        return SHA256Encrypt(strSrc, null);
    }


    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     */
    public static String SHA256Encrypt(String strSrc, String encName) {

        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }


    public static String bytes2Hex(byte[] bts) {

        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp.toUpperCase();
        }
        return des;
    }


    public static byte[] getByteFromBase64(String s) {

        byte[] b = null;
        if (s != null) {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }


    public static String decrypt2(String sSrc, String sKey) {

        String plaintext = null;
        try {

            byte[] raw = sKey.getBytes("utf-8");
            byte[] encrypted1 = SecurityUtil.getByteFromBase64(sSrc);
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return plaintext;
    }


    public static String decryptAes(String sSrc, String sKey){
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = SecurityUtil.getByteFromBase64(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }


    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryptAes(String content, String password) throws UnsupportedEncodingException {

        try {
            byte[] raw = password.getBytes("utf-8");
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            String encode = new sun.misc.BASE64Encoder().encode(result);
            return new String(encode); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
