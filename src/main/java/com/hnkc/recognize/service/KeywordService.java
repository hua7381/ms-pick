package com.hnkc.recognize.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnkc.recognize.dao.ContentDao;
import com.hnkc.recognize.model.po.Content;
import com.hnkc.recognize.model.vo.KeywordReg;
import com.hnkc.recognize.other.Tool;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {

    @Autowired
    ContentDao contentDao;

    private static final Integer PAGE_SIZE = 100;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void test1() {
        IPage<Content> page = new Page<Content>(1, PAGE_SIZE);
        LambdaQueryWrapper<Content> wrapper = Wrappers.lambdaQuery(Content.class);
        wrapper.orderByAsc(Content::getId);
        
        IPage<Content> res = contentDao.selectPage(page, wrapper);
        logger.info("pages: " + res.getPages());
        for(int i = 1; i<=page.getPages(); i++) {
            logger.info(String.format("handling page %s/%s", i, page.getPages()));
            eachPage(i, wrapper);
        }
        logger.info("finish");
    }

    private void eachPage(int pageNum, LambdaQueryWrapper<Content> wrapper) {
        IPage<Content> page = new Page<Content>(pageNum, PAGE_SIZE);
        IPage<Content> res = contentDao.selectPage(page, wrapper);
        for(Content one : res.getRecords()) {
            one.setKeywords(JSON.toJSONString(pickList(one.getContent())));
            contentDao.updateById(one);
        }
    }

    public List<String> pickList(String content) {
        List<String> res = new ArrayList<String>();
        String json = Tool.readFileFromClasspath("keywords.json");
        List<KeywordReg> regs = JSON.parseArray(json, KeywordReg.class);
        res = check(content, regs);
        return res;
    }

    private List<String> check(String content, List<KeywordReg> regs) {
        List<String> res = new ArrayList<String>();
        for (KeywordReg reg : regs) {
            String keyword = checkReg(content, reg);
            if (StringUtils.isNotEmpty(keyword)) {
                res.add(keyword);
                if(reg.getSubList() != null) {
                    for(KeywordReg subReg : reg.getSubList()) {
                        String subKeyword = checkReg(content, subReg);
                        if(StringUtils.isNotEmpty(subKeyword)) {
                            res.add(subKeyword);
                        }
                    }
                }
            }
        }
        return res;
    }

    private String checkReg(String content, KeywordReg reg) {
        boolean fit = false;
        for (String word : reg.getContainList()) {
            if (contain(content, word)) {
                fit = true;
                break;
            }
        }
        for (String word : reg.getExcludeList()) {
            if (contain(content, word)) {
                fit = false;
                break;
            }
        }
        if (fit) {
            return reg.getKeyword();
        } else {
            return null;
        }
    }

    private boolean contain(String content, String word) {
        if(StringUtils.isEmpty(word)) {
            return false;
        }
        if (word.contains("&")) {
            for (String subWord : word.split("&")) {
                if (!content.contains(subWord)) {
                    return false;
                }
            }
            return true;
        } else {
            return content.contains(word);
        }
    }

}
