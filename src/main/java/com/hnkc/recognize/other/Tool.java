package com.hnkc.recognize.other;

import com.hnkc.recognize.frame.shiro.LoginUser;

import org.apache.shiro.SecurityUtils;

/**
 * 工具类
 * @author zhangguihua
 * @date 2020/09/29
 */
public class Tool {

    public static LoginUser getLoginUser() {
        return (LoginUser)SecurityUtils.getSubject().getPrincipal();
    }

}