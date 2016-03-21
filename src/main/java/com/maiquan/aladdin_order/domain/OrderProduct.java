package com.maiquan.aladdin_order.domain;

import java.io.Serializable;

public class OrderProduct implements Serializable{

	private static final long serialVersionUID = -4376170385392828726L;

	private Integer ID;

    private Integer orderID;

    private Integer productID;

    private String productName;

    private String supName;

    private Integer skuID;
    
    private Integer buyNum;

    private String skuName;
    
    private Integer freightTplID;
    
    private String freightType;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
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

    public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

	public Integer getFreightTplID() {
		return freightTplID;
	}

	public void setFreightTplID(Integer freightTplID) {
		this.freightTplID = freightTplID;
	}

	public String getFreightType() {
		return freightType;
	}

	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}

	@Override
	public String toString() {
		return "OrderProduct [ID=" + ID + ", orderID=" + orderID
				+ ", productID=" + productID + ", productName=" + productName
				+ ", supName=" + supName + ", skuID=" + skuID + ", buyNum="
				+ buyNum + ", skuName=" + skuName + ", freightTplID="
				+ freightTplID + ", freightType=" + freightType + "]";
	}

}