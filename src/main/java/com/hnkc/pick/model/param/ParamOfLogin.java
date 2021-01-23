package com.hnkc.pick.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
@ApiModel("登录参数")
public class ParamOfLogin {
    @ApiModelProperty(value = "账号", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}