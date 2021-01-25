package com.hnkc.recognize.model.vo;

import com.hnkc.recognize.frame.Config;
import com.hnkc.recognize.frame.shiro.LoginUser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangguihua
 * @date 2020/11/30
 */
@ApiModel("页面初始化数据")
public class WebInitData {
    @ApiModelProperty("系统配置")
    private Config config;
    @ApiModelProperty("当前用户")
    private LoginUser currentUser;

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public LoginUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(LoginUser currentUser) {
        this.currentUser = currentUser;
    }
}