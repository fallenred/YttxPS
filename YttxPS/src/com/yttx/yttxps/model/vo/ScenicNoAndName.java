package com.yttx.yttxps.model.vo;


public class ScenicNoAndName {
    private String no;
    private String name;

    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}