package com.ssvmakers.amzo.autobuyscripts.Model;

public class FormModel {
    private String formAction;
    private String formMessage;
    private String primaryColor;

    public FormModel(String str, String str2, String str3) {
        this.formMessage = str;
        this.formAction = str2;
        this.primaryColor = str3;
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

    public String getPrimaryColor() {
        return this.primaryColor;
    }

    public void setPrimaryColor(String str) {
        this.primaryColor = str;
    }
}
