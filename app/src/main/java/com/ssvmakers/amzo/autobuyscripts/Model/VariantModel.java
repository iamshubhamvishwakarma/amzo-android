package com.ssvmakers.amzo.autobuyscripts.Model;

public class VariantModel {
    public String color;
    public String image;
    public String link;
    public String site;
    private final int deviceId;

    public VariantModel(int i, String str, String str2, String str3, String str4) {
        this.deviceId = i;
        this.color = str;
        this.image = str2;
        this.link = str3;
        this.site = str4;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String str) {
        this.site = str;
    }
}
