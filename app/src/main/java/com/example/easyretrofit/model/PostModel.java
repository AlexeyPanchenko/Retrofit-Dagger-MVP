package com.example.easyretrofit.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("desc")
    @Expose
    private String desc;

    @SerializedName("link")
    @Expose
    private String link;

    @SerializedName("elementPureHtml")
    @Expose
    private String elementPureHtml;

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getElementPureHtml() {
        return elementPureHtml;
    }

    public String getDesc() {
        return desc;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setElementPureHtml(String elementPureHtml) {
        this.elementPureHtml = elementPureHtml;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
