package com.hnkc.recognize.frame.shiro;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 自定义shiro过滤器
 * @author zhangguihua
 * @date 2020/09/29
 */
public class ShiroAuthFilter extends FormAuthenticationFilter {

    public ShiroAuthFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subj = super.getSubject(request, response);
        boolean bySession = subj.isAuthenticated();
        boolean byCookie = subj.isRemembered();
        // return bySession;
        return bySession || byCookie;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = resp.getWriter();
        writer.write(HttpServletResponse.SC_UNAUTHORIZED + "-unauthorized");
        writer.close();
        return false;
    }

}