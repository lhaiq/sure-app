package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;


@MapClass("com.hengsu.sure.invite.model.CashModel")
public class CashVO{


	@NumberFormat(style= NumberFormat.Style.NUMBER,pattern = "#,###.##")
	@Min(value = 1)
	private Double money;

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
	}
}