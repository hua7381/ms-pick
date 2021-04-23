package com.hnkc.recognize.other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 工具类
 * 
 * @author zhangguihua
 * @date 2020/09/29
 */
public class Tool {

    public static String readFileFromClasspath(String path) {
        BufferedReader r = null;
        StringBuffer sb = new StringBuffer();
        try {
            r = new BufferedReader(new FileReader(getClasspath() + path));
            String line = null;
            while ((line = r.readLine()) != null) {
                if(sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch(Exception e) {
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

    public static String getClasspath() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }

}