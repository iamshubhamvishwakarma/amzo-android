package com.ssvmakers.amzo.autobuyscripts.Model;


public class SliderItem {

    private String description;
    private String imageUrl;
    private String pageUrl;
    private String store;

    public SliderItem() {
    }

    public SliderItem(String description, String imageUrl, String pageUrl, String store) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.pageUrl = pageUrl;
        this.store = store;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}