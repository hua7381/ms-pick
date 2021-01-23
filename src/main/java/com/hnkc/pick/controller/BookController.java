package com.hnkc.pick.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hnkc.pick.model.param.ParamOfGetBook;
import com.hnkc.pick.model.po.Book;
import com.hnkc.pick.other.EntityTool;
import com.hnkc.pick.other.Tool;
import com.hnkc.pick.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 书籍controller
 * 说明：
 * 请使用Restful风格
 * BookController、service、dao、mapper是示例
 * @author zhangguihua
 * @date 2020/09/29
 */
@RestController
@RequestMapping("book")
@Api(tags = "书籍")
public class BookController {
    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService service;

    @ApiOperation("查询分页")
    @GetMapping("page")
    public IPage<Book> getPage(ParamOfGetBook param) {
        IPage<Book> page = service.getPage(param);
        for(Book one : page.getRecords()) {
            EntityTool.fillProps(one);
        }
        return page;
    }

    @ApiOperation("获取单个")
    @GetMapping("one/{id}")
    public Book getOne(@PathVariable("id") String id) {
        Book one = service.getOne(id);
        EntityTool.fillProps(one);
        return one;
    }

    @ApiOperation("新增")
    @PostMapping("one")
    public String postOne(@Validated(Book.Add.class) @RequestBody Book one) {
        one.setId(null);
        one.setCreateTime(null);
        one.setCreateUser(Tool.getLoginUser().getName());
        return service.postOne(one);
    }

    @ApiOperation("修改")
    @PutMapping("one")
    public void putOne(@Validated(Book.Update.class) @RequestBody Book one) {
        service.putOne(one);
    }

    @ApiOperation("删除")
    @DeleteMapping("one/{id}")
    public void deleteOne(@PathVariable("id") String id) {
        service.deleteOne(id);
    }

}
