package com.hnkc.recognize.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnkc.recognize.dao.ContentDao;
import com.hnkc.recognize.model.po.Content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final Integer PAGE_SIZE = 100;

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private ElementService elementService;

    public void batchTestByDb() {
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
            String content = one.getContent() + one.getDisposeSituation();
            one.setKeywords(JSON.toJSONString(keywordService.pickList(content)));
            one.setClues(JSON.toJSONString(elementService.pickList(content), SerializerFeature.PrettyFormat));
            contentDao.updateById(one);
        }
    }
    
}
