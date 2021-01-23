package com.hnkc.pick.model.po;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hnkc.pick.frame.Enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
@ApiModel("书籍")
@TableName("t_demo_book")
public class Book {
    public interface Add{};
    public interface Update{};

    //根据阿里规范，表必备三字段：id, create_time, update_time

    @ApiModelProperty("id")
    @NotEmpty(message = "id不能为空" ,groups = {Update.class})
    @TableId
    private String id;

    @TableField(update = "%s")// update时，若有值则覆盖
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE, update = "sysdate")// update时，固定填充
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("名称")
    @NotEmpty(message = "名称不能为空" ,groups = {Add.class})
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("发布时间")
    private Date publishTime;

    @ApiModelProperty("类别Id")
    @NotEmpty(message = "类别不能为空" ,groups = {Add.class})
    private String typeId;

    @ApiModelProperty("部门编号")
    @NotEmpty(message = "部门不能为空" ,groups = {Add.class})
    private String deptNo;

    @ApiModelProperty("状态:"+Enums.BOOK_STATUS_ENUM_DESCR)
    private String status;

    @ApiModelProperty("是否可用")
    private Boolean available;

    @ApiModelProperty("阅读量")
    private Integer readNum;

    // 以下为非表字段，用transient修饰

    @ApiModelProperty("类别名称")
    private transient String typeName;

    @ApiModelProperty("部门名称")
    private transient String deptName;

    @ApiModelProperty("状态名称")
    private transient String statusName;

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

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Book [available=" + available + ", createTime=" + createTime + ", createUser=" + createUser
                + ", deptName=" + deptName + ", deptNo=" + deptNo + ", description=" + description + ", id=" + id
                + ", name=" + name + ", publishTime=" + publishTime + ", readNum=" + readNum + ", status=" + status
                + ", statusName=" + statusName + ", typeId=" + typeId + ", typeName=" + typeName + ", updateTime="
                + updateTime + "]";
    }

}
