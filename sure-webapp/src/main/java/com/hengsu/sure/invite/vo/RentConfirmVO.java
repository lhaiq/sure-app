package com.hengsu.sure.invite.vo;

/**
 * Created by haiquanli on 15/12/25.
 */
public class RentConfirmVO {

    private String indentNo;
    private Long buyerId;
    private Double money;

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

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
}
