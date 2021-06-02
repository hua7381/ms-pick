package com.hnkc.recognize.service;

import java.util.ArrayList;
import java.util.List;

import com.hnkc.recognize.handler.AddressPicker;
import com.hnkc.recognize.handler.BankCardPicker;
import com.hnkc.recognize.handler.CasenoPicker;
import com.hnkc.recognize.handler.IdCardPicker;
import com.hnkc.recognize.handler.MobilePicker;
import com.hnkc.recognize.handler.PlatePicker;
import com.hnkc.recognize.model.po.Element;
import com.hnkc.recognize.other.EntityTool;

import org.springframework.stereotype.Service;

/**
 * @author zhangguihua
 * @date 2021年1月23日
 */
@Service
public class ElementService {

    public List<Element> pickList(String content) {
        List<Element> list = new ArrayList<Element>();
        if(content != null) {
            list.addAll(new MobilePicker().pick(content));
            list.addAll(new IdCardPicker().pick(content));
            list.addAll(new BankCardPicker().pick(content));
            list.addAll(new PlatePicker().pick(content));
            list.addAll(new AddressPicker().pick(content));
            list.addAll(new CasenoPicker().pick(content));
        }
        for (Element one : list) {
            EntityTool.fillProps(one);
        }
        return list;
    }

}