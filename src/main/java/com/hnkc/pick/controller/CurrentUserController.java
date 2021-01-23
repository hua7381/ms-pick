package com.hnkc.pick.controller;

import com.hnkc.pick.frame.shiro.LoginUser;
import com.hnkc.pick.model.param.ParamOfLogin;
import com.hnkc.pick.other.Tool;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 当前用户controller
 * @author zhangguihua
 * @date 2020/09/29
 */
@RestController
@RequestMapping("currentUser")
@Api(tags = "_当前用户")
public class CurrentUserController {

    @ApiOperation("获取当前登录用户")
    @GetMapping("currentUser")
    public LoginUser currentUser() {
        return Tool.getLoginUser();
    }

    @ApiOperation("登录")
    @PutMapping("login")
    public LoginUser login(@RequestBody ParamOfLogin param) {
        SecurityUtils.getSubject().login(new UsernamePasswordToken(param.getUsername(), param.getPassword(), true));
        return Tool.getLoginUser();
    }

    @ApiOperation("退出登录")
    @PutMapping("logout")
    public void logout() {
        SecurityUtils.getSubject().logout();
    }

}