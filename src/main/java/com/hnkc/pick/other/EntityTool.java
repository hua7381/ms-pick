package com.hnkc.pick.other;

import com.hnkc.pick.model.po.Book;
import com.hnkc.pick.frame.Enums;
import com.hnkc.pick.frame.dict.Dict;
import com.hnkc.pick.frame.tool.EnumTool;

/**
 * 实体类处理工具类
 * 将实体类返回给前端前的一些处理
 * @author zhangguihua
 * @date 2020/09/29
 */
public class EntityTool {

    /**
     * 翻译字典字段
     */
    public static void fillProps(Book one) {
        if(one != null) {
            one.setDeptName(Dict.getInstance().getDept().getMap().get(one.getDeptNo()));
            one.setStatusName(EnumTool.getLabel(Enums.BookStatusEnum.class, one.getStatus()));
        }
    }

}
