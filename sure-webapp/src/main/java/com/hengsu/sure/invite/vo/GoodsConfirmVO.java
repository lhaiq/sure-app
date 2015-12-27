package com.hengsu.sure.invite.vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by haiquanli on 15/12/27.
 */
public class GoodsConfirmVO {

    @NotEmpty
    private String indentNo;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;
    @NotNull
    private Double money;
    @NotNull
    private Integer invoice;
    @NotEmpty
    private String address;
    @NotEmpty
    private String receiveUserName;
    @NotEmpty
    private String receiveUserPhone;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getIndentNo() {
        return indentNo;
    }

    public void setIndentNo(String indentNo) {
        this.indentNo = indentNo;
    }

    public Integer getInvoice() {
        return invoice;
    }

    public void setInvoice(Integer invoice) {
        this.invoice = invoice;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReceiveUserName() {
        return receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public void setReceiveUserPhone(String receiveUserPhone) {
        this.receiveUserPhone = receiveUserPhone;
    }

    public String getReceiveUserPhone() {
        return receiveUserPhone;
    }
}
