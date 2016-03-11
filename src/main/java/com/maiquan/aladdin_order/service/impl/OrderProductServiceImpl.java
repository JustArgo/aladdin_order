package com.maiquan.aladdin_order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.maiquan.aladdin_order.domain.OrderProduct;
import com.maiquan.aladdin_order.mapper.OrderProductMapper;
import com.maiquan.aladdin_order.service.IOrderProductService;
import com.maiquan.aladdin_order.util.LogUtil;

public class OrderProductServiceImpl implements IOrderProductService{

	@Autowired
	private OrderProductMapper orderProductMapper;
	
	/**
	 * 根据订单商品的ID 获得订单商品对象
	 * @param orderProductID
	 * @param requestID
	 * @return
	 */
	@Override
	public OrderProduct getOrderProductByID(Integer orderProductID, String requestID) {
		
		LogUtil.logInput("订单商品微服务", "getOrderProductByID", requestID, orderProductID);
		
		OrderProduct orderProduct = orderProductMapper.selectByPrimaryKey(orderProductID);
		
		LogUtil.logOutput("订单商品微服务", "getOrderProductByID", requestID, orderProduct);
		
		return orderProduct;
	}

	/**
	 * 根据订单ID 获得订单商品列表
	 * @param orderID
	 * @param requestID
	 * @return
	 */
	@Override
	public List<OrderProduct> getOrderProductByOrderID(Integer orderID, String requestID) {
		
		LogUtil.logInput("订单商品微服务", "getOrderProductByOrderID", requestID, orderID);
		
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrderID(orderID);
		List<OrderProduct> orderProducts = orderProductMapper.selectByCondition(orderProduct);
		
		LogUtil.logOutput("订单商品微服务", "getOrderProductByOrderID", requestID, orderProducts);
		
		return orderProducts;
	}

	
	
}
