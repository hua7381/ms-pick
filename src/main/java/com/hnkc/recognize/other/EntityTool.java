package com.hnkc.recognize.other;

import com.hnkc.recognize.model.po.Element;
import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.frame.tool.EnumTool;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
public class EntityTool {

    public static void fillProps(Element one) {
        if (one != null) {
            one.setTypeName(EnumTool.getLabel(Enums.ElementType.class, one.getType()));
            if (one.getContent() != null) {
                one.setLength(one.getContent().length());
            }
        }
    }

}
