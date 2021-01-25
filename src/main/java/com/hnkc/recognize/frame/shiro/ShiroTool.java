package com.hnkc.recognize.frame.shiro;

import com.alibaba.fastjson.JSON;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;

/**
 * @author zhangguihua
 * @date 2020/12/01
 */
public class ShiroTool {

	public static void checkHasAnyRole(String... roles) {
    Subject subj = SecurityUtils.getSubject();
    for(String each : roles) {
      if(subj.hasRole(each)) {
        return;
      }
    }
    throw new UnauthorizedException("Subject does not have any role in " + JSON.toJSONString(roles));
	}

	public static void checkHasAllRole(String... roles) {
    Subject subj = SecurityUtils.getSubject();
    for(String each : roles) {
      if(!subj.hasRole(each)) {
        throw new UnauthorizedException("Subject does not have all role in " + JSON.toJSONString(roles));
      }
    }
	}

	public static void checkHasAnyPermission(String... permissions) {
    Subject subj = SecurityUtils.getSubject();
    for(String each : permissions) {
      if(subj.isPermitted(each)) {
        return;
      }
    }
    throw new UnauthorizedException("Subject does not have any permission in " + JSON.toJSONString(permissions));
	}

	public static void checkHasAllPermission(String... permissions) {
    Subject subj = SecurityUtils.getSubject();
    for(String each : permissions) {
      if(subj.isPermitted(each)) {
        throw new UnauthorizedException("Subject does not have all permission in " + JSON.toJSONString(permissions));
      }
    }
	}
  
}