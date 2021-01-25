package com.hnkc.recognize.frame.tool;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.hnkc.recognize.frame.shiro.LoginUser;

import org.apache.shiro.SecurityUtils;

/**
 * 日志工具
 * @author zhangguihua
 * @date 2020/09/29
 */
public class LogTool {

    /**
     * 获取需要记录到(ELK)日志中的字段
     * @return
     */
    public static Map<String, Object> props() {
        Map<String, Object> result = new HashMap<String, Object>(20);

        LoginUser user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        if (user != null) {
            result.put("user_no", user.getNo());
            result.put("user_name", user.getName());
            result.put("dept_no", user.getDeptNo());
            result.put("dept_name", user.getDeptName());
            result.put("user_ip", user.getIp());
        }
        
        return result;
    }

    /**
     * 获取异常精简后的堆栈
     * @param ex
     * @return
     */
    public static String simplifyTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String str = sw.toString();
        StringBuffer sb = new StringBuffer();
        String[] arr = str.split("\r\n");
        for (String line : arr) {
            if (!line.startsWith("\t") || line.startsWith("\tat com.hnkc")) {
                sb.append(line + "\n");
            }
        }
        return sb.toString();
    }

}