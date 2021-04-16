package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class IdCardPicker extends BasePicker implements Picker {

    @Override
    public List<Element> pick(String content) {
        String reg = 
            "[1-9]\\d{5}" + 
            "(19|20)\\d{2}" + // 年
            "((0[1-9])|(10|11|12))" + // 月
            "(([0-2][1-9])|10|20|30|31)" + // 日
            "\\d{3}[0-9Xx]";
        return find(content, reg, Enums.ElementType.ID_CARD.getId());
    }
    
}
