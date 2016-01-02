package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 16/1/2.
 */
public class InvitationPriceModel {

    private String time;
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
