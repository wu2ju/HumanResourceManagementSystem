package com.wuju.model;

public class Admin {
    private Integer adId;
    private String adAccount;
    private String adPassword;

    public Admin() {
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public String getAdAccount() {
        return adAccount;
    }

    public void setAdAccount(String adAccount) {
        this.adAccount = adAccount;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adId=" + adId +
                ", adAccount='" + adAccount + '\'' +
                ", adPassword='" + adPassword + '\'' +
                '}';
    }
}
