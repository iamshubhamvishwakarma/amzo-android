package com.ssvmakers.amzo.autobuyscripts.Model;

public class AdModel {
    private String banner;
    private Integer id;
    private String link;

    public AdModel(Integer num, String str, String str2) {
        this.id = num;
        this.banner = str;
        this.link = str2;
    }

    public String getBanner() {
        return this.banner;
    }

    public void setBanner(String str) {
        this.banner = str;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer num) {
        this.id = num;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
