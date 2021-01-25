package com.hnkc.recognize.frame.dict;

import io.swagger.annotations.ApiModel;

/**
 * 字典项
 * @author zhangguihua
 * @date 2020/09/29
 */
@ApiModel("字典项")
public class DictItem {
    private String id;
    private String pid;
    private String label;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
