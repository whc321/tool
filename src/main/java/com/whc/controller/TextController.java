package com.whc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang_haichun
 * @date 2019/2/21
 */
@Controller
public class TextController {

    @RequestMapping("/textDifference")
    public String base64(){
        return "textDifference/textDifference";
    }
}
