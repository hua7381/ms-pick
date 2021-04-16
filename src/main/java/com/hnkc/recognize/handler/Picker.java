package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.model.po.Element;

public interface Picker {

    public List<Element> pick(String content);
    
}
