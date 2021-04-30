package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hnkc.recognize.model.po.Element;

public class BasePicker {

    protected List<Element> find(String content, String reg, String type) {
        List<Element> list = new ArrayList<Element>();
        Set<String> set = new HashSet<String>();
        Matcher mather = Pattern.compile("\\D" + reg + "\\D").matcher("#" + content + "#");
        while (mather.find()) {
            Element ele = new Element();
            ele.setType(type);
            ele.setContent(cut(mather.group()));
            
            if(!set.contains(ele.getContent())) {
                list.add(ele);
                set.add(ele.getContent());
            }
        }
        return list;
    }

    private String cut(String str) {
        return str.substring(1, str.length() - 1);
    }

}
