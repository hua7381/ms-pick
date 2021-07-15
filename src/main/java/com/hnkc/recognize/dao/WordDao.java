package com.hnkc.recognize.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Select;

public interface WordDao extends BaseMapper<Object> {
    
    @Select("${sql}")
    List<String> getWords(String sql);

}