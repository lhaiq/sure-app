package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

@MapClass("com.hengsu.sure.invite.model.CashModel")
public class CashVO{

	private Double money;


	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
	}
}