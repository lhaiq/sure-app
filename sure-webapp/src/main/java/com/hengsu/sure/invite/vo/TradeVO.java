package com.hengsu.sure.invite.vo;

import com.hkntv.pylon.core.beans.mapping.annotation.MapClass;
import com.hkntv.pylon.core.beans.mapping.annotation.MapField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by haiquanli on 15/12/12.
 * http://doc.open.alipay.com/doc2/detail.htm?spm=0.0.0.0.0ww671&treeId=59&articleId=103666&docType=1
 */
@MapClass("com.hengsu.sure.invite.model.TradeModel")
public class TradeVO {

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @MapField("notifyTime")
    private Date notify_time;

    @MapField("notifyType")
    private String notify_type;

    @MapField("notifyId")
    private String notify_id;

    @MapField("signType")
    private String sign_type;

    private String sign;

    @MapField("outTradeNo")
    private String out_trade_no;

    private String subject;

    @MapField("paymentType")
    private String payment_type;

    @MapField("tradeNo")
    private String trade_no;

    @MapField("tradeStatus")
    private String trade_status;

    @MapField("sellerId")
    private String seller_id;

    @MapField("sellerEmail")
    private String seller_email;

    @MapField("buyerId")
    private String buyer_id;

    @MapField("buyerEmail")
    private String buyer_email;

    @MapField("totalFee")
    private Double total_fee;

    @MapField("notifyTime")
    private Integer quantity;

    private Double price;

    private String body;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @MapField("gmtCreate")
    private Date gmt_create;

    @MapField("gmtPayment")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date gmt_payment;

    @MapField("isTotalFeeAdjust")
    private String is_total_fee_adjust;

    @MapField("useCoupon")
    private String use_coupon;

    private String discount;

    @MapField("refundStatus")
    private String refund_status;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @MapField("gmtRefund")
    private Date gmt_refund;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBuyer_email() {
        return buyer_email;
    }

    public void setBuyer_email(String buyer_email) {
        this.buyer_email = buyer_email;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_payment() {
        return gmt_payment;
    }

    public void setGmt_payment(Date gmt_payment) {
        this.gmt_payment = gmt_payment;
    }

    public Date getGmt_refund() {
        return gmt_refund;
    }

    public void setGmt_refund(Date gmt_refund) {
        this.gmt_refund = gmt_refund;
    }

    public String getIs_total_fee_adjust() {
        return is_total_fee_adjust;
    }

    public void setIs_total_fee_adjust(String is_total_fee_adjust) {
        this.is_total_fee_adjust = is_total_fee_adjust;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public Date getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(Date notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
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

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Double total_fee) {
        this.total_fee = total_fee;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTrade_status() {
        return trade_status;
    }

    public void setTrade_status(String trade_status) {
        this.trade_status = trade_status;
    }

    public String getUse_coupon() {
        return use_coupon;
    }

    public void setUse_coupon(String use_coupon) {
        this.use_coupon = use_coupon;
    }
}
