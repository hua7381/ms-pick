package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class NamePicker extends BasePicker implements Picker {

    @Override
    public List<Element> pick(String content) {
        content = content.replace(":", "：").replace(",", "，").replace(".", "。").replace("(", "（").replace(")", "）");

        String reg = "((姓名[：]{0,1})|(疑人：)|(警人：)|(事主：))[\\u4e00-\\u9fa5]{2,4}[\\s，。）（]";
        List<Element> list = find(content, reg, Enums.ElementType.NAME.getId(), false);
        for (Element one : list) {
            String cont = one.getContent();
            System.out.println("cont: '" + cont + "'");
            one.setContent(cont.substring(cont.contains("：") ? 3 : 2, cont.length() - 1));
        }
        return list;
    }

}
