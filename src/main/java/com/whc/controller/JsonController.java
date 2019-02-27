package com.whc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang_haichun
 * @date 2019/2/21
 */
@Controller
@RequestMapping("/json")
public class JsonController {

    @RequestMapping("/jsonview")
    public String jsonView(){
        return "json/jsonview";
    }
}
