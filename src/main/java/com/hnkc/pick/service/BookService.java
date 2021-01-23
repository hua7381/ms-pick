package com.hnkc.pick.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hnkc.pick.dao.BookDao;
import com.hnkc.pick.frame.exception.BizException;
import com.hnkc.pick.frame.tool.LogTool;
import com.hnkc.pick.model.param.ParamOfGetBook;
import com.hnkc.pick.model.po.Book;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书籍service
 * @author zhangguihua
 * @date 2020/09/29
 */
@Service
public class BookService {
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookDao dao;

    public String postOne(Book one) {
        logger.info("新增Book"+JSON.toJSONString(one), LogTool.props());
        dao.insert(one);
        return one.getId();
    }

    public void putOne(Book one) {
        logger.info("修改Book"+JSON.toJSONString(one), LogTool.props());
        int num = dao.updateById(one);
        if(num == 0) {
            throw new BizException("id不存在");
        }
    }

    public IPage<Book> getPage(ParamOfGetBook param) {
        IPage<Book> page = new Page<Book>(param.getPageNum(), param.getPageSize());

        // 动态查询条件
        LambdaQueryWrapper<Book> wrapper = Wrappers.lambdaQuery(Book.class)
        .apply("1 = 1")
        .ge(param.getBeginTime() != null, Book::getCreateTime, param.getBeginTime())
        .le(param.getEndTime() != null, Book::getCreateTime, param.getEndTime())
        // 嵌套or语句
        .and(StringUtils.isNotEmpty(param.getKeyword()), i -> i 
            .like(Book::getName, param.getKeyword())
            .or()
            .like(Book::getDescription, param.getKeyword())
        );

        // 排序
        if(param.order() != null) {
            wrapper.last(param.order());
        } else {
            // 默认排序
            wrapper.orderByDesc(Book::getCreateTime).orderByAsc(Book::getId);
        }

        return dao.selectPage(page, wrapper);// mbp通用查询
    }

    public Book getOne(String id) {
        return dao.selectById(id);// mbp通用查询
    }

    public void deleteOne(String id) {
        logger.info("删除Book: "+id, LogTool.props());
        int num = dao.deleteById(id);
        if(num == 0) {
            throw new BizException("id不存在");
        }
    }

}
