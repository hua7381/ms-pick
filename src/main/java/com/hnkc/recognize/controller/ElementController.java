package com.hnkc.recognize.controller;

import java.util.List;

import com.hnkc.recognize.model.param.ParamOfPick;
import com.hnkc.recognize.model.po.Element;
import com.hnkc.recognize.other.EntityTool;
import com.hnkc.recognize.service.ElementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author zhangguihua
 * @date 2021年1月23日
 */
@Api(tags = "要素")
@RestController
@RequestMapping("element")
public class ElementController {

    @Autowired
    private ElementService service;

    @ApiOperation("提取")
    @PostMapping("pick")
    public List<Element> pickList(@RequestBody ParamOfPick param) {
        List<Element> list = service.pickList(param.getText());
        for (Element one : list) {
            EntityTool.fillProps(one);
        }
        return list;
    }

}