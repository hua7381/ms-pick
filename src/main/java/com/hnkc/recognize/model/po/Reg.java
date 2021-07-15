package com.hnkc.recognize.model.po;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("匹配规则")
@TableName("t_recognize_reg")
public class Reg {
    @ApiModelProperty("结果关键词")
    private String keyword;

    @ApiModelProperty("包含词s")
    private String contains;

    @ApiModelProperty("排除词s")
    private String excludes;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getExcludes() {
        return excludes;
    }

    public void setExcludes(String excludes) {
        this.excludes = excludes;
    }

}