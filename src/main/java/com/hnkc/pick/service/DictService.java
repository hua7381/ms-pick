package com.hnkc.pick.service;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hnkc.pick.dao.BookTypeDao;
import com.hnkc.pick.model.po.BookType;
import com.hnkc.pick.frame.dict.DictItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
@Service
public class DictService {

    @Autowired
    private BookTypeDao bookTypeDao;

    public List<DictItem> getBookTypes() {
        List<DictItem> res = new ArrayList<DictItem>();
        List<BookType> list = bookTypeDao.selectList(new LambdaQueryWrapper<BookType>().orderByDesc(BookType::getCreateTime));
        list.forEach(item -> {
            DictItem di = new DictItem();
            di.setId(item.getId());
            di.setLabel(item.getName());
            res.add(di);
        });
        return res;
    }
  
}