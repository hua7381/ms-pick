package com.hnkc.recognize.model.po;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
@ApiModel("书籍类别")
@TableName("t_demo_book_type")
public class BookType {

  @ApiModelProperty("id")
  private String id;

  @TableField(update = "%s")
  @ApiModelProperty("创建时间")
  private Date createTime;

  @TableField(update = "sysdate")
  @ApiModelProperty("更新时间")
  private Date updateTime;

  @ApiModelProperty("名称")
  private String name;

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

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}