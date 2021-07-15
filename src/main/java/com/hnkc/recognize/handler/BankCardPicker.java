package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
public class BankCardPicker extends BasePicker implements Picker {

    @Override
    public List<Element> pick(String content) {
        String reg = 
            "(62\\d{17})|(62\\d{14})";
        return find(content, reg, Enums.ElementType.BANK_CARD.getId());
    }
    
}
