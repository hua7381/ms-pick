package com.hnkc.recognize.frame.shiro;

import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义shiro认证实现
 * @author zhangguihua
 * @date 2020/09/29
 */
@Component
public class ShiroAuthRealm extends AuthorizingRealm {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "123";
    private static final String BY_JSON = "_BY_JSON";

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户输入的token
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;

        String username = utoken.getUsername();
        String password = new String(utoken.getPassword());

        LoginUser user = new LoginUser();
        // 测试用
        if (DEFAULT_USERNAME.equals(username) && DEFAULT_PASSWORD.equals(password)) {
            user.setId("36a718a997ba49f7a8710621eed58a95");
            user.setNo("00001");
            user.setName("管理员");
            user.setDeptNo("440100");
            user.setDeptName("部门A");
            
        } else if (BY_JSON.equals(password)) {
            String json = username;
            @SuppressWarnings("unchecked")
            Map<String, Object> obj = JSON.parseObject(json, Map.class);

            user.setId((String)obj.get("id"));
            user.setNo((String)obj.get("no"));
            user.setName((String)obj.get("name"));
            
        } else {
            throw new AccountException("登录失败");
        }

        return new SimpleAuthenticationInfo(user, password, "");
    }

    /**
     * 授权, 授予角色与权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("ROLE1");
        info.addStringPermission("PERMISSION1");
        return info;
    }

}