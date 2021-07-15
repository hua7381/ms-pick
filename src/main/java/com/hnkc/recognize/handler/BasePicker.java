package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hnkc.recognize.model.po.Element;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
public class BasePicker {

    protected List<Element> find(String content, String reg, String type) {
        return find(content, reg, type, true);
    }

    protected List<Element> find(String content, String reg, String type, Boolean isNumber) {
        List<Element> list = new ArrayList<Element>();
        Set<String> set = new HashSet<String>();
        Matcher mather = null;
        if (isNumber) {
            mather = Pattern.compile("\\D" + reg + "\\D").matcher("#" + content + "#");
        } else {
            mather = Pattern.compile(reg).matcher(content);
        }
        while (mather.find()) {
            Element ele = new Element();
            ele.setType(type);
            ele.setContent(isNumber ? cut(mather.group()) : mather.group());
            
            if (!set.contains(ele.getContent())) {
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
