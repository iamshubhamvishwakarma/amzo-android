package com.ssvmakers.amzo.autobuyscripts.Model;

public class Deal {
    String id;
    String storeName;
    String url;
    String image;
    String oldPrice;
    String newPrice;
    String name;
    String primaryColor;

    public Deal(String id, String storeName, String url, String image, String oldPrice, String newPrice, String name, String primaryColor) {
        this.id = id;
        this.storeName = storeName;
        this.url = url;
        this.image = image;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.name = name;
        this.primaryColor = primaryColor;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }
}
