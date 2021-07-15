package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.model.po.Element;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
public interface Picker {

    public List<Element> pick(String content);
    
}
