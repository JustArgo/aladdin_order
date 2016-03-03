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
	public int addOrderProduct(OrderProduct orderProduct, String requestID) {
		
		orderProductMapper.insert(orderProduct);
		return 0;
		
	}

	@Override
	public OrderProduct getOrderProductByID(Integer orderProductID, String requestID) {
		
		OrderProduct orderProduct = orderProductMapper.selectByPrimaryKey(orderProductID);
		
		return orderProduct;
	}

	@Override
	public int deleteOrderProduct(Integer orderProductID, String requestID) {
		
		orderProductMapper.deleteByPrimaryKey(orderProductID);
		
		return 0;
	}

	@Override
	public int updateOrderProduct(OrderProduct orderProduct, String requetsID) {
		
		orderProductMapper.updateByPrimaryKeySelective(orderProduct);
		
		return 0;
	}

	@Override
	public List<OrderProduct> getOrderProductsByOrderID(Integer orderID, String requestID) {
		
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setOrderID(orderID+"");
		return orderProductMapper.selectByCondition(orderProduct);

	}

	@Override
	public List<OrderProduct> getOrderProductsByProductName(String productName,
			String requestID) {

		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setProductName(productName);
		return orderProductMapper.selectByCondition(orderProduct);
		
	}

	@Override
	public List<OrderProduct> getOrderProductsBySupName(String supName,
			String requestID) {
		
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setSupName(supName);
		return orderProductMapper.selectByCondition(orderProduct);

	}

	
	
}
