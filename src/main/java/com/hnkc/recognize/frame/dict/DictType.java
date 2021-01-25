package com.hnkc.recognize.frame.dict;

import java.util.List;
import java.util.Map;

/**
 * 一类字典(字典项的集合)
 * @author zhangguihua
 * @date 2020/09/29
 */
public class DictType {
    private List<DictItem> list;
    private Map<String,String> map;

    public DictType() {
    }

    public DictType(List<DictItem> list, Map<String, String> map) {
        this.list = list;
        this.map = map;
    }

    public List<DictItem> getList() {
        return list;
    }
    public void setList(List<DictItem> list) {
        this.list = list;
    }
    public Map<String, String> getMap() {
        return map;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
