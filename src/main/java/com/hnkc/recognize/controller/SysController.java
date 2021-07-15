package com.hnkc.recognize.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
@RestController
@RequestMapping("sys")
@ApiIgnore
public class SysController {

    @GetMapping("heartbeat")
    @ApiOperation("心跳")
    public String heartbeat() {
        return System.currentTimeMillis() + "";
    }

}