package com.hnkc.pick.controller;

import java.util.List;

import com.hnkc.pick.frame.Enums;
import com.hnkc.pick.frame.dict.Dict;
import com.hnkc.pick.frame.dict.DictItem;
import com.hnkc.pick.frame.tool.EnumTool;
import com.hnkc.pick.service.DictService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 字典controller
 * @author zhangguihua
 * @date 2020/09/29
 */
@RestController
@RequestMapping("dict")
@Api(tags = "_字典")
public class DictController {
    
    Logger logger = LoggerFactory.getLogger(DictController.class);

    @Autowired
    private DictService dictService;

    @ApiOperation("获取一类字典项列表")
    @GetMapping("itemList")
	@ApiImplicitParams({
		@ApiImplicitParam(name="type", value="类别: "+Enums.DICT_TYPE_ENUM_DESCR, paramType = "query", required = true),
	})
    public List<DictItem> getItemList(String type) {
        if(Enums.DictTypeEnum.BOOK_STATUS.getId().equals(type)) {
            return EnumTool.getEnumDictType(Enums.BookStatusEnum.class);

        } else if(Enums.DictTypeEnum.BOOK_TYPE.getId().equals(type)) {
            return dictService.getBookTypes();

        } else if(Enums.DictTypeEnum.DEPT.getId().equals(type)) {
            return Dict.getInstance().getDept().getList();

        } else {
            throw new IllegalArgumentException("字典类别不存在: "+type);

        }
    }
    
}
