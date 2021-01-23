package com.hnkc.pick.controller;

import com.hnkc.pick.frame.shiro.ShiroTool;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * 测试controller
 * @author zhangguihua
 * @date 2020/09/29
 */
@RestController
@RequestMapping("test")
public class TestController {
  
  @ApiOperation("模拟异常：内部错误(bug)")
  @GetMapping("err500")
  public String err500() {
    if(System.currentTimeMillis() > 1L) {
      System.out.println(1/0);
    }
    return "xxx";
  }
  
  @ApiOperation("模拟异常：输入的参数错误")
  @GetMapping("err400")
  public String err400() {
    if(System.currentTimeMillis() > 1L) {
      throw new IllegalArgumentException("输入的参数错误（测试）");
    }
    return "xxx";
  }
  
  @ApiOperation("模拟异常：无访问权限")
  @GetMapping("err403")
  public String err403() {
    SecurityUtils.getSubject().checkRole("ROLExxxxx");
    return "xxx";
  }

  // --------------示例：角色与权限控制的
  
  @GetMapping("requireRole1")
  public String requireRole1() {
    SecurityUtils.getSubject().checkRole("ROLE1");
    return "xxx";
  }
  
  @GetMapping("requireRole2")
  public String requireRole2() {
    SecurityUtils.getSubject().checkRole("ROLE2");
    return "xxx";
  }
  
  @GetMapping("requireRole1Or2")
  public String requireRole1Or2() {
    ShiroTool.checkHasAnyRole("ROLE1", "ROLE2");
    return "xxx";
  }
  
  @GetMapping("requireRole2Or3")
  public String requireRole2Or3() {
    ShiroTool.checkHasAnyRole("ROLE2", "ROLE3");
    return "xxx";
  }
  
  @GetMapping("requireRole1And2")
  public String requireRole1And2() {
    ShiroTool.checkHasAllRole("ROLE1", "ROLE2");
    return "xxx";
  }
  
  @GetMapping("requirePermission1")
  public String requirePermission1() {
    SecurityUtils.getSubject().checkPermission("PERMISSION1");
    return "xxx";
  }
  
  @GetMapping("requirePermission2")
  public String requirePermission2() {
    SecurityUtils.getSubject().checkPermission("PERMISSION2");
    return "xxx";
  }
  
  @GetMapping("requirePermission1Or2")
  public String requirePermission1Or2() {
    ShiroTool.checkHasAnyPermission("PERMISSION1", "PERMISSION2");
    return "xxx";
  }
  
  @GetMapping("requirePermission2Or3")
  public String requirePermission2Or3() {
    ShiroTool.checkHasAnyPermission("PERMISSION2", "PERMISSION3");
    return "xxx";
  }
  
  @GetMapping("requirePermission1And2")
  public String requirePermission1And2() {
    ShiroTool.checkHasAllPermission("PERMISSION1", "PERMISSION2");
    return "xxx";
  }

}