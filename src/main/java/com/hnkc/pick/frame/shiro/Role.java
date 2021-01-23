package com.hnkc.pick.frame.shiro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * @description: 角色
 * @author: zhangguihua
 * @time: 2020/11/24 9:07
 */
@ApiModel("角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 6833721472686293089L;

    @ApiModelProperty("角色代码")
    private String code;

    @ApiModelProperty("角色名称")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
