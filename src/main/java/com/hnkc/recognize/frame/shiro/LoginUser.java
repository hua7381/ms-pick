package com.hnkc.recognize.frame.shiro;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
@ApiModel("当前用户")
public class LoginUser implements Serializable {
    private static final long serialVersionUID = -7768836108287796716L;
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("编号/账号/警号")
    private String no;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("部门编号")
    private String deptNo;
    @ApiModelProperty("部门名称")
    private String deptName;
    @ApiModelProperty("ip地址")
    private String ip;
    @ApiModelProperty("角色列表")
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}