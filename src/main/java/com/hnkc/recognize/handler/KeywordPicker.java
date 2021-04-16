package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

public class KeywordPicker extends BasePicker implements Picker {

    private static String[] keywords = { "噪音", "维权", "纠纷", "激烈争吵", "走失" };

    @Override
    public List<Element> pick(String content) {
        List<Element> res = new ArrayList<Element>();
        for(String keyword : keywords) {
            if(content.contains(keyword)) {
                Element ele = new Element();
                ele.setType("TODO");
                ele.setTypeName("TODO");
                ele.setLength(0);
                ele.setContent(keyword);
                res.add(ele);
            }
        }
        // TODO 一个不断丰富完善的关键词库，目前只能开发人员人工添加
        return res;
    }

}
