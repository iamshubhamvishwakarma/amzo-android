package com.ssvmakers.amzo.autobuyscripts.Model;

public class OnBoardModel {
    private int image;
    private int onBoardColor;
    private String onBoardText1;
    private String onBoardText2;
    private String onBoardText3;
    private String onBoardTitle;

    public OnBoardModel(String str, int i, int i2, String str2, String str3, String str4) {
        this.onBoardTitle = str;
        this.onBoardColor = i;
        this.image = i2;
        this.onBoardText1 = str2;
        this.onBoardText2 = str3;
        this.onBoardText3 = str4;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int i) {
        this.image = i;
    }

    public int getOnBoardColor() {
        return this.onBoardColor;
    }

    public void setOnBoardColor(int i) {
        this.onBoardColor = i;
    }

    public String getOnBoardText1() {
        return this.onBoardText1;
    }

    public void setOnBoardText1(String str) {
        this.onBoardText1 = str;
    }

    public String getOnBoardText2() {
        return this.onBoardText2;
    }

    public void setOnBoardText2(String str) {
        this.onBoardText2 = str;
    }

    public String getOnBoardText3() {
        return this.onBoardText3;
    }

    public void setOnBoardText3(String str) {
        this.onBoardText3 = str;
    }

    public String getOnBoardTitle() {
        return this.onBoardTitle;
    }

    public void setOnBoardTitle(String str) {
        this.onBoardTitle = str;
    }
}
