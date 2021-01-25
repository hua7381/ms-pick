package com.hnkc.recognize.dao;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hnkc.recognize.frame.dict.DictItem;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统功能DAO
 * @author zhangguihua
 * @date 2020/09/29
 */
@Mapper
public interface SysDao extends BaseMapper<Object> {

    /**
     * 获取当前数据库时间
     * @return
     */
    public Date getNow();

    /**
     * 获取部门字典
     * @return
     */
	public List<DictItem> getDepts();

}