package com.hnkc.recognize.frame.exception.handler;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 404处理
 * @author zhangguihua
 * @date 2020/09/29
 */
@Controller
public class MyErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(value = ERROR_PATH)
    public String handleError(HttpServletResponse resp) {
        return "404-抱歉,访问的地址不存在";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
