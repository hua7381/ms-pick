package com.hnkc.recognize.model.vo;

import java.util.List;

public class KeywordReg {
    private String keyword;
    private List<String> containList;
    private List<String> excludeList;
    private List<KeywordReg> subList;

    public List<KeywordReg> getSubList() {
        return subList;
    }

    public void setSubList(List<KeywordReg> subList) {
        this.subList = subList;
    }

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
