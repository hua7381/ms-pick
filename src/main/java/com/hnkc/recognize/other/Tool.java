package com.hnkc.recognize.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 工具类
 * 
 * @author zhangguihua
 * @date 2020/09/29
 */
public class Tool {

    public static String readFileFromClasspath(String path) {
        StringBuffer sb = new StringBuffer();
        BufferedReader r = null;
        try {
            r = new BufferedReader(
                    new InputStreamReader(Tool.class.getClassLoader().getResourceAsStream(path), "utf-8"));
            String line = null;
            while ((line = r.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}