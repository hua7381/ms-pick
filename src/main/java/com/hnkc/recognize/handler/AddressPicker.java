package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class AddressPicker implements Picker {
    String[] beginTags = { "地址:", "地:" };
    String[] endTags = { " ", ",", ".", "\n" };

    @Override
    public List<Element> pick(String str) {
        List<Element> res = new ArrayList<Element>();
        str = format(str);

        for(String tag : beginTags) {
            res.addAll(eachTag(tag, str));
        }
        
        return res;
    }

    private String format(String str) {
        return str.replace("，", ",").replace("。", ".").replace("：", ":");
    }

    private List<Element> eachTag(String tag, String str) {
        List<Element> res = new ArrayList<Element>();
        List<Integer> begins = getBegins(str, tag);

        for(Integer begin : begins) {
            res.addAll(eachBegin(str, begin, tag));
        }

        return res;
    }

    private List<Element> eachBegin(String str, Integer begin, String tag) {
        List<Element> resList = new ArrayList<Element>();

        String part = str.substring(begin + tag.length());
        Integer end = findEnd(part);
        if(end != null) {
            String one = part.substring(0, end);
            Element bean = new Element();
            bean.setType(Enums.ElementType.ADDRESS.getId());
            bean.setTypeName(Enums.ElementType.ADDRESS.getLabel());
            bean.setContent(one);
            bean.setLength(one.length());
            resList.add(bean);
        }

        return resList;
    }

    private List<Integer> getBegins(String str, String tag) {
        List<Integer> res = new ArrayList<Integer>();
        
        int index = 0;
        int sum = 0;
        do {
            index = str.indexOf(tag);
            if(index != -1) {
                sum += index;
                res.add(sum);
                str = str.substring(index + tag.length());
                sum += tag.length();
            }
        } while(index != -1);

        return res;
    }

    private Integer findEnd(String str) {
        Integer res = null;
        for(String end : endTags) {
            Integer num = str.indexOf(end);
            if(num != -1 && (res == null || num < res)) {
                res = num;
            }
        }
        return res;
    }

}
