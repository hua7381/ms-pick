package com.hnkc.recognize.frame.shiro;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * shiro的配置
 * @author zhangguihua
 * @date 2020/09/29
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // bean.setLoginUrl("/login111");

        // 配置访问权限, anon 可匿名访问
        LinkedHashMap<String, String> chainMap = new LinkedHashMap<>();
        // swagger
        chainMap.put("/swagger-ui.html", "anon");
        chainMap.put("/webjars/**", "anon");
        chainMap.put("/v2/**", "anon");
        chainMap.put("/swagger-resources/**", "anon");
        // others
        chainMap.put("/", "anon");
        chainMap.put("/authBack", "anon");
        chainMap.put("/sys/**", "anon");
        chainMap.put("/dict/**", "anon");
        chainMap.put("/currentUser/**", "anon");
        // chainMap.put("/test/**", "authc");
        // chainMap.put("/**", "MyShiroAuthFilter");
        chainMap.put("/**", "anon");
        bean.setFilterChainDefinitionMap(chainMap);

        Map<String, Filter> filters = new HashMap<String, Filter>(10);
        filters.put("MyShiroAuthFilter", new ShiroAuthFilter());
        bean.setFilters(filters);

        return bean;
    }

    /**
     * 配置核心安全事务管理器
     * @param authRealm
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") ShiroAuthRealm authRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        manager.setRememberMeManager(rememberMeManager());
        return manager;
    }

    /**
     * 配置自定义的权限登录器
     * @return
     */
    @Bean(name = "authRealm")
    public ShiroAuthRealm authRealm() {
        ShiroAuthRealm myAuthRealm = new ShiroAuthRealm();
        //开启内存缓存
        myAuthRealm.setCacheManager(new MemoryConstrainedCacheManager());
        return myAuthRealm;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCookie(rememberMeCookie());
        // cookie加密的秘钥
        manager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag == "));
        return manager;
    }

    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("me");
        // 生效时间单位秒, -1 代表'直到浏览器关闭'
        cookie.setMaxAge(-1);
        return cookie;
    }

}