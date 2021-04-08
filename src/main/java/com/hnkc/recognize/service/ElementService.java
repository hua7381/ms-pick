package com.hnkc.recognize.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

import org.springframework.stereotype.Service;

/**
 * @author zhangguihua
 * @date 2021年1月23日
 */
@Service
public class ElementService {

    public List<Element> pickList(String content) {
        List<Element> list = new ArrayList<Element>();
        if(content != null) {
            list.addAll(getMobiles(content));
            list.addAll(getIdCards(content));
            list.addAll(getBankCards(content));
            list.addAll(getPlates(content));
        }
        return list;
    }

    private List<Element> getMobiles(String content) {
        String reg = "1[3|4|5|7|8][0-9]{9}";
        return find(content, reg, Enums.ElementType.MOBILE.getId());
    }

    private List<Element> getIdCards(String content) {
        String reg = 
            "[1-9]\\d{5}" + 
            "(19|20)\\d{2}" + // 年
            "((0[1-9])|(10|11|12))" + // 月
            "(([0-2][1-9])|10|20|30|31)" + // 日
            "\\d{3}[0-9Xx]";
        return find(content, reg, Enums.ElementType.ID_CARD.getId());
    }

    private List<Element> getBankCards(String content) {
        String reg = 
            "(62\\d{17})|(62\\d{14})";
        return find(content, reg, Enums.ElementType.BANK_CARD.getId());
    }

    private List<Element> getPlates(String content) {
        String reg = 
            "([京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新][ABCDEFGHJKLMNPQRSTUVWXY][1-9DF][1-9ABCDEFGHJKLMNPQRSTUVWXYZ]\\d{3}[1-9DF]"+
            "|"+
            "[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼渝川贵云藏陕甘青宁新][ABCDEFGHJKLMNPQRSTUVWXY][\\dABCDEFGHJKLNMxPQRSTUVWXYZ]{5})";
        return find(content, reg, Enums.ElementType.PLATE.getId());
    }

    private List<Element> find(String content, String reg, String type) {
        List<Element> list = new ArrayList<Element>();
        Matcher mc = Pattern.compile(reg).matcher(content);
        while(mc.find()) {
            Element ele = new Element();
            ele.setType(type);
            ele.setContent(mc.group());
            list.add(ele);
        }
        return list;
    }

}