package com.whc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by zhang_jian3 on 2017/7/17.
 */
public class SignatureUtil {
    private static Logger logger = LoggerFactory.getLogger(SignatureUtil.class);


    public static String generateSignature(final Map<String, Object> data, String key) {
        String sign = mapSort(data) + "_" + key;
        return MakeMD5.getMd5ByString(sign);
    }

    /**
     * map里数据排序
     * @param data
     * @return
     */
    private static String mapSort(final Map<String, Object> data){
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (data.get(k) != null && String.valueOf(data.get(k)).trim().length() > 0){
                sb.append(k).append("=").append(String.valueOf(data.get(k)).trim()).append("&");
            }
        }
        String sign = sb.toString();
        if(!StringUtils.isEmpty(sign)){
            sign = sign.substring(0,sign.length() - 1);
        }
        return sign;
    }


    /**
     * 生成随机数当作getItemID
     * n ： 需要的长度
     * @return
     */
    public static String getItemID(int n) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            String str = random.nextInt(2) % 2 == 0 ? "num" : "char";
            if ("char".equalsIgnoreCase(str)) {
                int nextInt = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (nextInt + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(str)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    public static String getWxSign(final Map<String, Object> data){
        String sign = mapSort(data);
        return SHA1Util.SHA1(sign);
    }

}
