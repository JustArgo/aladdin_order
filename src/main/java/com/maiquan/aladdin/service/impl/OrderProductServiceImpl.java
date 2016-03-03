package com.maiquan.aladdin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.maiquan.aladdin.domain.OrderProduct;
import com.maiquan.aladdin.mapper.OrderProductMapper;
import com.maiquan.aladdin.service.IOrderProductService;

public class OrderProductServiceImpl implements IOrderProductService{

	@Autowired
	private OrderProductMapper orderProductMapper;
	
	@Override
	public OrderProduct getOrderProductByID(Integer orderProductID) {
		
		OrderProduct orderProduct = orderProductMapper.selectByPrimaryKey(orderProductID);
		
		return orderProduct;
	}

	@Override
	public int deleteOrderProduct(Integer orderProductID, String requestID) {
		
		orderProductMapper.deleteByPrimaryKey(orderProductID);
		
		return 0;
	}

	@Override
	public int updateOrderProduct(OrderProduct orderProduct, Integer requetsID) {
		
		orderProductMapper.updateByPrimaryKeySelective(orderProduct);
		
		return 0;
	}

	@Override
	public List<OrderProduct> getOrderProductsByOrderID(Integer orderID, String requestID) {
		
		
		return null;
	}

	@Override
	public List<OrderProduct> getOrderProductsByProductName(String productName,
			String requestID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderProduct> getOrderProductsBySupName(String supName,
			String requestID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
