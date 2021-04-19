package com.ssvmakers.amzo.autobuyscripts.Model;

import java.util.List;

public class FlashSaleModel {
    public static final int AD = 1;
    public static final int FORM = 3;
    public static final int NORMAL = 0;
    public static final int TIPS = 2;
    public int id;
    public String image;
    public String name;
    public String primaryColor;
    public String site;
    public List<VariantModel> variantModels;
    private Integer adId;
    private int adapterType;
    private String banner;
    private String buttonText;
    private String formAction;
    private String formMessage;
    private String link;
    private String primColor;
    private String salesTiming;
    private String tipsMessage;
    private String tipsTitle;
    private String webLink;

    public FlashSaleModel(int i, String str, String str2, String str3, String str4, String str5, int i2) {
        this.id = i;
        this.name = str;
        this.image = str2;
        this.site = str3;
        this.primaryColor = str4;
        this.salesTiming = str5;
        this.adapterType = i2;
    }

    public FlashSaleModel(AdModel adModel, int i) {
        this.adId = adModel.getId();
        this.banner = adModel.getBanner();
        this.link = adModel.getLink();
        this.adapterType = i;
    }

    public FlashSaleModel(FormModel formModel, int i) {
        this.primaryColor = formModel.getPrimaryColor();
        this.formMessage = formModel.getFormMessage();
        this.formAction = formModel.getFormAction();
        this.adapterType = i;
    }

    public FlashSaleModel(TipsModel tipsModel, int i) {
        this.webLink = tipsModel.getWebLink();
        this.primColor = tipsModel.getPrimColor();
        this.tipsMessage = tipsModel.getTipsMessage();
        this.tipsTitle = tipsModel.getTipsTitle();
        this.buttonText = tipsModel.getButtonText();
        this.adapterType = i;
    }

    public Integer getAdId() {
        return this.adId;
    }

    public void setAdId(Integer num) {
        this.adId = num;
    }

    public int getAdapterType() {
        return this.adapterType;
    }

    public void setAdapterType(int i) {
        this.adapterType = i;
    }

    public String getBanner() {
        return this.banner;
    }

    public void setBanner(String str) {
        this.banner = str;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public void setButtonText(String str) {
        this.buttonText = str;
    }

    public String getFormAction() {
        return this.formAction;
    }

    public void setFormAction(String str) {
        this.formAction = str;
    }

    public String getFormMessage() {
        return this.formMessage;
    }

    public void setFormMessage(String str) {
        this.formMessage = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPrimColor() {
        return this.primColor;
    }

    public void setPrimColor(String str) {
        this.primColor = str;
    }

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setPrimaryColor(String str) {
        this.primaryColor = str;
    }

    public String getSalesTiming() {
        return this.salesTiming;
    }

    public void setSalesTiming(String str) {
        this.salesTiming = str;
    }

    public String getSite() {
        return this.site;
    }

    public void setSite(String str) {
        this.site = str;
    }

    public String getTipsMessage() {
        return this.tipsMessage;
    }

    public void setTipsMessage(String str) {
        this.tipsMessage = str;
    }

    public String getTipsTitle() {
        return this.tipsTitle;
    }

    public void setTipsTitle(String str) {
        this.tipsTitle = str;
    }

    public List<VariantModel> getVariantModels() {
        return this.variantModels;
    }

    public void setVariantModels(List<VariantModel> list) {
        this.variantModels = list;
    }

    public String getWebLink() {
        return this.webLink;
    }

    public void setWebLink(String str) {
        this.webLink = str;
    }
}
