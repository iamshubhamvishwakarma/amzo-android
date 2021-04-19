package com.ssvmakers.amzo.autobuyscripts.Model;

public class TipsModel {
    private String buttonText;
    private String primColor;
    private String tipsMessage;
    private String tipsTitle;
    private String webLink;

    public TipsModel(String str, String str2, String str3, String str4, String str5) {
        this.webLink = str5;
        this.primColor = str4;
        this.tipsMessage = str;
        this.tipsTitle = str2;
        this.buttonText = str3;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public void setButtonText(String str) {
        this.buttonText = str;
    }

    public String getPrimColor() {
        return this.primColor;
    }

    public void setPrimColor(String str) {
        this.primColor = str;
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

    public String getWebLink() {
        return this.webLink;
    }

    public void setWebLink(String str) {
        this.webLink = str;
    }
}
