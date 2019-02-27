package com.whc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wang_haichun
 * @date 2018/11/12
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error/{code}")
    public String error(@PathVariable int code) {
        String pager = "error/";
        switch (code) {
            case 400:
                pager += "400";
                break;
            case 403:
                pager += "400";
                break;
            case 404:
                pager += "404";
                break;
            case 500:
                pager += "500";
                break;
            default:
                pager += "404";
                break;
        }
        return pager;
    }
}
