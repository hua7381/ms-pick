package com.hnkc.recognize.controller;

import java.util.List;

import com.hnkc.recognize.model.param.ParamOfPickKeywords;
import com.hnkc.recognize.service.KeywordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "关键词")
@RestController
@RequestMapping("keyword")
public class KeywordController {

    @Autowired
    private KeywordService service;
    
    @ApiOperation("提取")
    @PostMapping("pickList")
    public List<String> pickList(@RequestBody ParamOfPickKeywords content) {
        return service.pickList(content.getContent());
    }

}
