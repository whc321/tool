package com.whc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang_haichun
 * @date 2019/2/21
 */
@Controller
public class Md5Controller {

    @RequestMapping("/md5")
    public String base64(){
        return "md5/md5";
    }
}
