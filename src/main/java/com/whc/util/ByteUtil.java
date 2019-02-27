package com.whc.util;

import java.util.Arrays;

/**
 * 字节工具。
 * 
 * 提供数据在字节数组和可见字符串之间转化的功能。
 * 
 * @author ge_yongshan
 */
public class ByteUtil {

    private static final Integer BYTE_UTIL_RADIX = 16;

    private static final Integer BYTE_UTIL_POSITION = 4;

    /**
     * 将16进制字符串还原为原始字节数组。
     * 
     * @param hexStr 16进制字符串
     * @return 原始字节数组
     * 
     * @throws NullPointerException 参数为null时抛出
     * @throws IllegalArgumentException 参数中包含16进制码元以外的字符
     */
    public static byte[] toBytes(String hexStr) {

        int length = hexStr.length();

        // 奇数修正,前补0
        int revision = 0;
        if (length % 2 != 0) {
            revision = 1;
        }

        char[] charArr = new char[length + revision];
        Arrays.fill(charArr, '0');
        System.arraycopy(hexStr.toCharArray(), revision, charArr, 0, length);

        byte[] bytes = new byte[charArr.length / 2]; // 恢复成字节后，容量减少一半
        for (int i = 0; i < charArr.length; i++) {
            char c = Character.toLowerCase(charArr[i]);
            int b = 0;
            if (c >= '0' && c <= '9') {
                b = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                b = c - 'a' + 10;
            } else {
                throw new IllegalArgumentException("\"" + hexStr + "\" has illegal char:'" + c + "'");
            }

            bytes[i / 2] <<= BYTE_UTIL_POSITION;
            bytes[i / 2] += (byte) b;
        }

        return bytes;
    }


    /**
     * 将原始字节数组转化为16进制字符串。
     * 
     * @param bytes 字节数组
     * @return 16进制字符串
     * 
     * @throws NullPointerException 参数为null时抛出
     */
    public static String toHex(byte[] bytes) {

        char[] charArr = new char[bytes.length * 2]; // 字节展开后占据2个char的位置
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            int low = b & 0x0F;
            int high = (b & 0xF0) >>> BYTE_UTIL_POSITION;

            charArr[i * 2] = Character.forDigit(high, BYTE_UTIL_RADIX);
            charArr[i * 2 + 1] = Character.forDigit(low, BYTE_UTIL_RADIX);
        }

        return new String(charArr);
    }

}