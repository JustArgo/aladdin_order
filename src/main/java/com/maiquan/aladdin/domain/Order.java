package com.maiquan.aladdin.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable{
	
	private static final long serialVersionUID = 5995521368154777239L;

	//支付宝支付
	public static final String PAY_CHANNEL_ALIPAY     = "ALI"; 
	//微信支付
	public static final String PAY_CHANNEL_WECHATPAY  = "WXP";
	//贝宝支付
	public static final String PAY_CHANNEL_PAYPALPAY  = "PAY"; 
	
	private Integer orderID;

    private Integer parentID;

    private String orderCode;

    private String orderStatus;

    private String payChannel;

    private String payStatus;

    private String mqID;

    private String invoiceName;

    private String country;

    private String province;

    private String city;

    private String district;

    private String address;

    private String recName;

    private String recMobile;

    private Long postFee;

    private Long orderPrice;

    private Long payPrice;

    private Date confirmTime;

    private Date payTime;

    private Date createTime;

    private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
    
    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus == null ? null : payStatus.trim();
    }

    public String getMqID() {
        return mqID;
    }

    public void setMqID(String mqID) {
        this.mqID = mqID == null ? null : mqID.trim();
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName == null ? null : invoiceName.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRecName() {
        return recName;
    }

    public void setRecName(String recName) {
        this.recName = recName == null ? null : recName.trim();
    }

    public String getRecMobile() {
        return recMobile;
    }

    public void setRecMobile(String recMobile) {
        this.recMobile = recMobile == null ? null : recMobile.trim();
    }

    public Long getPostFee() {
        return postFee;
    }

    public void setPostFee(Long postFee) {
        this.postFee = postFee;
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Long payPrice) {
        this.payPrice = payPrice;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", parentID=" + parentID
				+ ", orderCode=" + orderCode + ", orderStatus=" + orderStatus
				+ ", payChannel=" + payChannel + ", payStatus=" + payStatus
				+ ", mqID=" + mqID + ", invoiceName=" + invoiceName
				+ ", country=" + country + ", province=" + province + ", city="
				+ city + ", district=" + district + ", address=" + address
				+ ", recName=" + recName + ", recMobile=" + recMobile
				+ ", postFee=" + postFee + ", orderPrice=" + orderPrice
				+ ", payPrice=" + payPrice + ", confirmTime=" + confirmTime
				+ ", payTime=" + payTime + ", createTime=" + createTime
				+ ", orderProducts=" + orderProducts + "]";
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
    
    
    
}