package com.hengsu.sure.invite.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by haiquanli on 15/12/20.
 */
public class InvitationConfirmVO {

    @NotNull
    private Long receivedUserId;
    @NotEmpty
    private String indentNo;
    @NotNull
    private Double money;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;

    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(Long receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
