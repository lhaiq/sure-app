package com.hengsu.sure.invite.model;

/**
 * Created by haiquanli on 15/12/27.
 */
public class CancelIndentModel {

    private Long id;

    private Integer expireHour;

    private Double poundage;

    private Double rate;

    private Double money;

    public Integer getExpireHour() {
        return expireHour;
    }

    public void setExpireHour(Integer expireHour) {
        this.expireHour = expireHour;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getPoundage() {
        return poundage;
    }

    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
