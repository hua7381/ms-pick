package com.hnkc.recognize.model.vo;

import java.util.List;

public class KeywordReg {
    private String keyword;
    private List<String> containList;
    private List<String> excludeList;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getContainList() {
        return containList;
    }

    public void setContainList(List<String> containList) {
        this.containList = containList;
    }

    public List<String> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<String> excludeList) {
        this.excludeList = excludeList;
    }
}
