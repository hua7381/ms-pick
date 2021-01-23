package com.hnkc.pick.controller;

import com.hnkc.pick.frame.Config;
import com.hnkc.pick.model.vo.WebInitData;
import com.hnkc.pick.other.Tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统功能controller
 * @author zhangguihua
 * @date 2020/09/29
 */
@RestController
@RequestMapping("sys")
@Api(tags = "_系统功能")
public class SysController {

    @Autowired
    private Config config;

    @GetMapping("webInitData")
    @ApiOperation("获取页面初始化数据")
    public WebInitData getWebInitData() {
        WebInitData result = new WebInitData();
        result.setConfig(config);
        result.setCurrentUser(Tool.getLoginUser());
        return result;
    }

    @GetMapping("config")
    @ApiOperation("配置")
    public Config config() {
        return config;
    }

    @GetMapping("heartbeat")
    @ApiOperation("心跳")
    public String heartbeat() {
        return System.currentTimeMillis() + "";
    }

    @GetMapping("testException")
    @ApiOperation("测试异常")
    public String testException() {
        System.out.println(1/0);
        return "hi";
    }

}