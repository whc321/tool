package com.whc.controller;

import com.alibaba.fastjson.JSONObject;
import com.whc.util.AESUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang_haichun
 * @date 2019/2/21
 */
@Controller
public class CryptaesController {

    @RequestMapping("/cryptaes")
    public String cryptaes(){
        return "cryptaes/cryptaes";
    }

    /**
     *
     * @param data
     * @param type
     * @param arg  m=ecb_pad=zero_block=128_p=xqsjT9yzcY0AQhjl_i=2222_o=1_s=gb2312_t=0
     * @return
     */
    @RequestMapping(value = "cryptaes",method = RequestMethod.POST)
    @ResponseBody
    public String cryptaesCalc(String data,String type,String arg){
        Map<String,String> map = new HashMap<>();
        String[] args = arg.split("_");
        for(String str : args){
            String[] value = str.split("=");
            map.put(value[0],value[1]);
        }
        //加密模式
        String m = map.get("m");
        //补码方式
        String pad = map.get("pad");
        //数据块位数
        Integer block = Integer.valueOf(map.get("block"));
        //加密密码
        String p = map.get("p");
        //偏移量
        String i = map.get("i");
        //返回类型 0：base64 1：hex
        String o = map.get("o");
        //编码方式
        String s = map.get("s");
        //0：加密  1：解密
        String t = map.get("t");

        //算法/模式/补码方式
        String tokenizer = type.toUpperCase() + "/" + m.toUpperCase() + "/" + this.getComplementMethod(pad);

        String returnData = "";
        if("0".equals(t)){
            //加密
            returnData = AESUtil.encrypt(p,data,type,block,tokenizer,s,i,o);
        }else {
            //解密
            returnData = AESUtil.decrypt(p,data,type,block,tokenizer,s,i,o);
        }
        JSONObject returnMap = new JSONObject();
        List<String> list = new ArrayList<>();
        list.add(returnData);
        returnMap.put("status",1);
        returnMap.put("info","ok");
        returnMap.put("data",list);
        return returnMap.toJSONString();
    }


    private String getComplementMethod(String type){
        String complementMethod = "";
        switch (type){
            case "no":
                complementMethod = "NoPadding";
                break;
            case "pkcs5":
                complementMethod = "PKCS5Padding";
                break;
            case "pkcs7":
                complementMethod = "PKCS7Padding";
                break;
            case "zero":
                complementMethod = "ZeroBytePadding";
                break;
            case "iso10126":
                complementMethod = "ISO10126Padding";
                break;
            case "ansix923":
                complementMethod = "X9.23PADDING";
                break;
            default:
                break;
        }
        return complementMethod;
    }
}
