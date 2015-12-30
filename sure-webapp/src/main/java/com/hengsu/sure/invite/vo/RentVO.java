package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@MapClass("com.hengsu.sure.invite.model.RentModel")
public class RentVO{
	
	private Long id;

	@NotNull
	private Integer city;

	@NotEmpty
	private List<String> scenes;

	@NotNull
	private Integer gender;

	@NotEmpty
	private List<String> dates;
	private String time;
	private Integer car;

	@NotNull
	private Double price;

	@NotNull
	private Double money;

	@NotEmpty
	private String state;
	private Date createTime;
	private Integer status;

	private List<Long> imageIds;

	public Integer getCar() {
		return car;
	}

	public void setCar(Integer car) {
		this.car = car;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}

	public List<Long> getImageIds() {
		return imageIds;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getScenes() {
		return scenes;
	}

	public void setScenes(List<String> scenes) {
		this.scenes = scenes;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getMoney() {
		return money;
	}
}