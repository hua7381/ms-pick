package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class AddressPicker extends BasePicker implements Picker {

    @Override
    public List<Element> pick(String content) {
        content = content.replace(":", "：").replace(",", "，").replace(".", "。").replace("(", "（").replace(")", "）");

        String reg = "((地点：)|(地址：)|(住址：)|(报：在)|(称：在))[a-zA-Z0-9\\u4e00-\\u9fa5]{4,}[\\)|\\s|，|\\,|。|\\.|）]";
        
        List<Element> list = find(content, reg, Enums.ElementType.ADDRESS.getId(), false);
        for (Element one : list) {
            one.setContent(one.getContent().substring(3, one.getContent().length() - 1));
        }
        return list;
    }

}
