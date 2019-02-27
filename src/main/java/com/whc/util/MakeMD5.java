package com.whc.util;


import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MakeMD5 {
    private static final Logger logger = Logger.getLogger(MakeMD5.class);

    private static final int MD5_TMP_NUM = 256;

    private static final int MD5_NUM = 16;

    /**
     * md5加密
     *
     * @param source
     * @return
     */
    public static String getMd5ByString(String source) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            byte[] inputByteArray = source.getBytes("UTF-8");
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArr2HexStr(resultByteArray).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * md5加密
     *
     * @param arrB
     * @return
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {

        int iLen = arrB.length;

        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];

            while (intTmp < 0) {
                intTmp += MD5_TMP_NUM;
            }

            if (intTmp < MD5_NUM) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, MD5_NUM));
        }
        return sb.toString();
    }
}
