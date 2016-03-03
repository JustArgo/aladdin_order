package com.maiquan.aladdin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.maiquan.aladdin.domain.Order;
import com.maiquan.aladdin.mapper.OrderMapper;
import com.maiquan.aladdin.service.IOrderService;

public class OrderServiceImpl implements IOrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public Order getOrderByID(Integer orderID, String requestID) {
		
		Order order = orderMapper.selectByPrimaryKey(orderID);
		
		return order;
	}

	@Override
	public int addOrder(Order order, String requestID) {
		
		orderMapper.insert(order);
		
		return 0;
	}

	@Override
	public int deleteOrder(Integer orderID, String requestID) {
		
		orderMapper.deleteByPrimaryKey(orderID);
		
		return 0;
	}

	@Override
	public int updateOrder(Order order, String requestID) {
		
		orderMapper.updateByPrimaryKeySelective(order);
		
		return 0;
	}

	@Override
	public Order getOrderByOrderCode(String orderCode, String requestID) {
		
		Order order = new Order();
		order.setOrderCode(orderCode);
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		if(orders.size()>0){
			return orders.get(0);
		}
		
		return null;
	}

	@Override
	public List<Order> getOrderByMqID(String mqID, String requestID) {
		
		Order order = new Order();
		order.setMqID(mqID);
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		return orders;
	}

	@Override
	public int divideOrder(Order order, String requestID) {
		return 0;
	}

	@Override
	public List<Order> getOrderByOrderStatus(String orderStatus, String requestID){
		
		Order order = new Order();
		order.setOrderStatus(orderStatus);
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		return orders;
		
	}
	
	@Override
	public List<Order> getOrderByPayStatus(String payStatus, String requestID) {
		
		Order order = new Order();
		order.setPayStatus(payStatus);
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		return orders;
	}
	
	@Override
	public List<Order> getOrderByPayChannel(String channel, String requestID){
		
		Order order = new Order();
		order.setPayChannel(channel);
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		return orders;
	}
}
