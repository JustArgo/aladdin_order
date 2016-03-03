package com.maiquan.aladdin.service;

import java.util.List;

import com.maiquan.aladdin.domain.Order;

public interface IOrderService {
	
	/**
	 * 根据订单ID查找订单
	 * @param orderID
	 * @param requestID
	 * @return
	 */
	Order getOrderByID(Integer orderID, String requestID);
	
	/**
	 * 新增订单
	 * @param order
	 * @param requestID
	 * @return
	 */
	int addOrder(Order order, String requestID);
	
	/**
	 * 删除订单 
	 * @param orderID
	 * @param requestID
	 * @return
	 */
	int deleteOrder(Integer orderID, String requestID);
	
	/**
	 * 修改订单
	 * @param order
	 * @param requestID
	 * @return
	 */
	int updateOrder(Order order, String requestID);
	
	/**
	 * 根据订单号查询订单
	 * @param orderCode
	 * @param requetID
	 * @return
	 */
	Order getOrderByOrderCode(String orderCode, String requestID);
	
	/**
	 * 根据下单用户的ID查询订单
	 * @param mqID
	 * @param requestID
	 * @return
	 */
	List<Order> getOrderByMqID(String mqID, String requestID);
	
	/**
	 * 拆分订单
	 * @param order
	 * @param requestID
	 * @return
	 */
	int divideOrder(Order order, String requestID);

	/**
	 * 根据支付状态查找订单
	 * @param status
	 * @param requestID
	 * @return
	 */
	List<Order> getOrderByPayStatus(String status, String requestID);
	
	/**
	 * 根据支付渠道查找订单
	 * @param channle
	 * @param requestID
	 * @return
	 */
	List<Order> getOrderByPayChannel(String channle, String requestID);
}
