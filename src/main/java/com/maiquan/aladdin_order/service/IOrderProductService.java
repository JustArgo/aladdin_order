package com.maiquan.aladdin_order.service;

import java.util.List;

import com.maiquan.aladdin_order.domain.OrderProduct;

public interface IOrderProductService {

	
	OrderProduct getOrderProductByID(Integer orderProductID, String requestID);
	
	List<OrderProduct> getOrderProductByOrderID(Integer orderID, String requestID);
	
}
