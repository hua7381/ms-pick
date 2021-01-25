package com.hnkc.recognize.frame.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.hnkc.recognize.frame.Constants;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;

/**
 * 自定义日志输出格式（logstash要求的JSON格式）
 * @author zhangguihua
 * @date 2020/09/29
 */
public class MyLogLayout extends LayoutBase<ILoggingEvent> {

    @Override
    public String doLayout(ILoggingEvent event) {
        Map<String,Object> logObj = new HashMap<String,Object>(20);
        logObj.put("log_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        logObj.put("message", event.getMessage());
        logObj.put("logger_name", event.getLoggerName());
        logObj.put("thread_name", event.getThreadName());
        logObj.put("level", event.getLevel().toString());
        // 系统名称
        logObj.put("app_name", Constants.SYS_NAME);
        putExtraProps(logObj, event);
        return JSON.toJSONString(logObj)+"\n";
    }

    private Map<String,Object> putExtraProps(Map<String,Object> logObj, ILoggingEvent event) {
        Object[] arr = event.getArgumentArray();
        if(arr != null && arr.length == 1) {
            if(arr[0] instanceof Map) {
                Map<String, Object> extraProps = convert(arr[0]);
                for(Object key : extraProps.keySet()) {
                    logObj.put((String)key, extraProps.get(key));
                }
            } else {
                // none
            }
        }
        return logObj;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> convert(Object obj) {
        return (Map<String,Object>)obj;
    }

}
