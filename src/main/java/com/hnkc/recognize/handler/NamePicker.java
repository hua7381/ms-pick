package com.hnkc.recognize.handler;

import java.util.ArrayList;
import java.util.List;

import com.hnkc.recognize.frame.Enums;
import com.hnkc.recognize.model.po.Element;

/**
 * @author zhangguihua
 * @date 2021年7月15日
 */
public class NamePicker extends BasePicker implements Picker {
    /*
    事主李艳娇（女，49岁，容桂人，联系电话：13392220063）报称其父亲李林洪（男，77岁，容桂人，家属反映有老人痴呆，无带通讯工具）
    被盗，车主:李细文，车辆于多年前购买
    现其朋友（何慧怡，26岁，13428350463，家居在容桂
    */

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

        String reg2 = "[（：][\\u4e00-\\u9fa5]{2,4}，[男女]";
        for (Element one : find(content, reg2, Enums.ElementType.NAME.getId(), false)) {
            String cont = one.getContent();
            one.setContent(cont.substring(1, cont.length() - 2));
            list.add(one);
        }

        return list;
    }

}
