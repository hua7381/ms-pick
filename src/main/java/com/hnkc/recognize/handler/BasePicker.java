package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hnkc.recognize.model.po.Element;

public class BasePicker {

    protected List<Element> find(String content, String reg, String type) {
        List<Element> list = new ArrayList<Element>();
        Matcher mc = Pattern.compile(reg).matcher(content);
        while (mc.find()) {
            Element ele = new Element();
            ele.setType(type);
            ele.setContent(mc.group());
            list.add(ele);
        }
        return list;
    }

}
