package com.example.showadvertisement.bean;

public class ResourceBean {
    private int type;
    private String url;


    public ResourceBean() {
    }

    public ResourceBean(int type, String url) {
        this.type = type;
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
