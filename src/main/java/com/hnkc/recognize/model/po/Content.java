package com.hnkc.recognize.model.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;

@ApiModel("测试content")
@TableName("t_recognize_content")
public class Content {
    private String id;
    private Date createTime;
    private String content;
    private String keywords;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
