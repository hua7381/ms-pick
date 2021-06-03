package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class NamePicker extends BasePicker implements Picker {

    @Override
    public List<Element> pick(String content) {
        List<Element> list = new ArrayList<Element>();
        content = content.replace(":", "：").replace(",", "，").replace(".", "。").replace("(", "（").replace(")", "）");

        String reg = "((姓名[：]{0,1})|(疑人：)|(警人：)|(事主：))[\\u4e00-\\u9fa5]{2,4}[\\s，。）（]";
        for (Element one : find(content, reg, Enums.ElementType.NAME.getId(), false)) {
            String cont = one.getContent();
            one.setContent(cont.substring(cont.contains("：") ? 3 : 2, cont.length() - 1));
            list.add(one);
        }

        String reg2 = "（[\\u4e00-\\u9fa5]{2,4}，[男女]";
        for (Element one : find(content, reg2, Enums.ElementType.NAME.getId(), false)) {
            String cont = one.getContent();
            one.setContent(cont.substring(1, cont.length() - 2));
            list.add(one);
        }

        return list;
    }

}
