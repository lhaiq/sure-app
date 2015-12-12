package com.hengsu.sure.invite.model;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import com.hkntv.pylon.core.beans.mapping.annotation.MapField;

import java.util.Date;

@MapClass("com.hengsu.sure.invite.entity.Trade")
public class TradeModel{
	
	private Long id;
	private Date notifyTime;
	private String notifyType;
	private String notifyId;
	private String signType;
	private String sign;
	private String outTradeNo;
	private String subject;
	private String paymentType;
	private String tradeNo;
	private String tradeStatus;
	private String sellerId;
	private String sellerEmail;
	private String buyerId;
	private String buyerEmail;
	private Double totalFee;
	private Integer quantity;
	private Double price;
	private String body;
	private Date gmtCreate;
	private Date gmtPayment;
	private String isTotalFeeAdjust;
	private String useCoupon;
	private String discount;
	private String refundStatus;
	private Date gmtRefund;
		
	public void setId(Long id){
		this.id = id;
	}
	
	public Long getId(){
		return this.id;
	}
		
	public void setNotifyTime(Date notifyTime){
		this.notifyTime = notifyTime;
	}
	
	public Date getNotifyTime(){
		return this.notifyTime;
	}
		
	public void setNotifyType(String notifyType){
		this.notifyType = notifyType;
	}
	
	public String getNotifyType(){
		return this.notifyType;
	}
		
	public void setNotifyId(String notifyId){
		this.notifyId = notifyId;
	}
	
	public String getNotifyId(){
		return this.notifyId;
	}
		
	public void setSignType(String signType){
		this.signType = signType;
	}
	
	public String getSignType(){
		return this.signType;
	}
		
	public void setSign(String sign){
		this.sign = sign;
	}
	
	public String getSign(){
		return this.sign;
	}
		
	public void setOutTradeNo(String outTradeNo){
		this.outTradeNo = outTradeNo;
	}
	
	public String getOutTradeNo(){
		return this.outTradeNo;
	}
		
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	public String getSubject(){
		return this.subject;
	}
		
	public void setPaymentType(String paymentType){
		this.paymentType = paymentType;
	}
	
	public String getPaymentType(){
		return this.paymentType;
	}
		
	public void setTradeNo(String tradeNo){
		this.tradeNo = tradeNo;
	}
	
	public String getTradeNo(){
		return this.tradeNo;
	}
		
	public void setTradeStatus(String tradeStatus){
		this.tradeStatus = tradeStatus;
	}
	
	public String getTradeStatus(){
		return this.tradeStatus;
	}
		
	public void setSellerId(String sellerId){
		this.sellerId = sellerId;
	}
	
	public String getSellerId(){
		return this.sellerId;
	}
		
	public void setSellerEmail(String sellerEmail){
		this.sellerEmail = sellerEmail;
	}
	
	public String getSellerEmail(){
		return this.sellerEmail;
	}
		
	public void setBuyerId(String buyerId){
		this.buyerId = buyerId;
	}
	
	public String getBuyerId(){
		return this.buyerId;
	}
		
	public void setBuyerEmail(String buyerEmail){
		this.buyerEmail = buyerEmail;
	}
	
	public String getBuyerEmail(){
		return this.buyerEmail;
	}
		
	public void setTotalFee(Double totalFee){
		this.totalFee = totalFee;
	}
	
	public Double getTotalFee(){
		return this.totalFee;
	}
		
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}
	
	public Integer getQuantity(){
		return this.quantity;
	}
		
	public void setPrice(Double price){
		this.price = price;
	}
	
	public Double getPrice(){
		return this.price;
	}
		
	public void setBody(String body){
		this.body = body;
	}
	
	public String getBody(){
		return this.body;
	}
		
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}
	
	public Date getGmtCreate(){
		return this.gmtCreate;
	}
		
	public void setGmtPayment(Date gmtPayment){
		this.gmtPayment = gmtPayment;
	}
	
	public Date getGmtPayment(){
		return this.gmtPayment;
	}
		
	public void setIsTotalFeeAdjust(String isTotalFeeAdjust){
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}
	
	public String getIsTotalFeeAdjust(){
		return this.isTotalFeeAdjust;
	}
		
	public void setUseCoupon(String useCoupon){
		this.useCoupon = useCoupon;
	}
	
	public String getUseCoupon(){
		return this.useCoupon;
	}
		
	public void setDiscount(String discount){
		this.discount = discount;
	}
	
	public String getDiscount(){
		return this.discount;
	}
		
	public void setRefundStatus(String refundStatus){
		this.refundStatus = refundStatus;
	}
	
	public String getRefundStatus(){
		return this.refundStatus;
	}
		
	public void setGmtRefund(Date gmtRefund){
		this.gmtRefund = gmtRefund;
	}
	
	public Date getGmtRefund(){
		return this.gmtRefund;
	}
		
		
}