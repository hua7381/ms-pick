package com.hnkc.recognize.handler;

import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
public class MobilePicker extends BasePicker implements Picker {
    
    public List<Element> pick(String content) {
        String reg = "1[3|4|5|7|8][0-9]{9}";
        return find(content, reg, Enums.ElementType.MOBILE.getId());
    }

}
