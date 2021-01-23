package com.hnkc.pick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 根路径controller
 * @author zhangguihua
 * @date 2020/09/29
 */
@Controller
@RequestMapping("/")
@ApiIgnore
public class RootController {

    /**
     * 用于根路径跳转
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String index() {
        return "redirect:/ui/";
    }

}
