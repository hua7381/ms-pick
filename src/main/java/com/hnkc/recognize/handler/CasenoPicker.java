package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class CasenoPicker extends BasePicker implements Picker {

    @Override
    public List<Element> pick(String content) {
        String reg = "A44\\d{20}";
        return find(content, reg, Enums.ElementType.CASE_NO.getId());
    }
    
}
