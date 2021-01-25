package com.hnkc.recognize.model.param;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangguihua
 * @date 2020/09/29
 */
@ApiModel("分页查询参数")
public class ParamOfGetPage {
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNum;
    @ApiModelProperty(value = "分页大小", required = true)
    private Integer pageSize;
    @ApiModelProperty(value = "排序属性")
    private String orderProp;
    @ApiModelProperty(value = "排序方向")
    private Boolean isAsc;
    @ApiModelProperty(value = "搜索关键词", required = false)
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间", required = false)
    private Date beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间", required = false)
    private Date endTime;
    
	public String order() {
        return 
        (this.orderProp == null || this.orderProp.length() == 0) ?
        null 
        :
        "order by "+this.orderProp.replaceAll("[A-Z]", "_$0").toLowerCase() + (new Boolean(true).equals(this.getIsAsc()) ? " asc" : " desc");
    }
    
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderProp() {
        return orderProp;
    }

    public void setOrderProp(String orderProp) {
        this.orderProp = orderProp;
    }

    public Boolean getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean isAsc) {
        this.isAsc = isAsc;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
