package com.whc.controller;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by wang_haichun on 2017/7/26.
 */
@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);




    @RequestMapping("/")
    public String indexOne(HttpServletRequest request,String order) {
        return "index";
    }

    @RequestMapping("index")
    public String indexTwo(HttpServletRequest request,String order) {
        return "index";
    }










    /**
     * BASE64转码
     * @param str
     * @return
     */
    private String getBase64(String str){
        try {
            return new String(Base64.decodeBase64(str),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error("BASE64转码失败:",e);
        }
        return null;
    }

}
