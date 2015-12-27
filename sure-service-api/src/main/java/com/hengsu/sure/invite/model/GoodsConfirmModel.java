package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 15/12/27.
 */
public class GoodsConfirmModel {

    private Long id;
    private String indentNo;
    private Long buyerId;
    private Double price;
    private Integer quantity;
    private Double money;
    private Integer invoice;
    private String address;
    private String receiveUserName;
    private String receiveUserPhone;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReceiveUserPhone() {
        return receiveUserPhone;
    }

    public void setReceiveUserPhone(String receiveUserPhone) {
        this.receiveUserPhone = receiveUserPhone;
    }
}
