package com.maiquan.aladdin.domain;

import java.io.Serializable;

public class OrderProduct implements Serializable{
	
	private static final long serialVersionUID = -910026698410684439L;

	private Integer ID;

    private String orderID;

    private Integer product;

    private String productName;

    private String supName;

    private Integer skuID;

    private String skuName;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID == null ? null : orderID.trim();
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName == null ? null : supName.trim();
    }

    public Integer getSkuID() {
        return skuID;
    }

    public void setSkuID(Integer skuID) {
        this.skuID = skuID;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

	@Override
	public String toString() {
		return "OrderProduct [ID=" + ID + ", orderID=" + orderID + ", product="
				+ product + ", productName=" + productName + ", supName="
				+ supName + ", skuID=" + skuID + ", skuName=" + skuName + "]";
	}
    
}