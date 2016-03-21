package com.maiquan.aladdin_order.service;

import java.util.List;

import com.maiquan.aladdin_order.domain.Order;

public interface IOrderService {
	
	/**
	 * 根据订单ID查找订单
	 */
	Order getOrderByID(Integer orderID, String requestID);
	
	/**
	 * 根据主订单id 查找子订单列表
	 */
	List<Order> getChildOrdersByParentOrderID(Integer orderID, String requestID);
	
	/**
	 * 新增订单
	 */
	int placeOrder(String mqID, Integer[] skuIDs, Integer[] buyNums, Long[] skuPrices, Long pFee, Long pSum, String invoiceName, String notes, String requestID);
	
	/**
	 * 删除订单 
	 */
	int deleteOrder(Integer orderID, String requestID);
	
	/**
	 * 修改订单
	 */
	int updateOrder(Order order, String requestID);
	
	/**
	 * 根据订单号查询订单
	 */
	Order getOrderByOrderCode(String orderCode, String requestID);
	
	/**
	 * 根据下单用户的ID查询订单
	 */
	List<Order> getOrderByMqID(String mqID, String requestID);
	
	/**
	 * 拆分订单
	 */
	int divideOrder(Order order, String requestID);

	/**
	 * 根据订单状态查找订单
	 */
	List<Order> getOrderByOrderStatus(String mqID, String orderStatus, String requestID);
	
	/**
	 * 根据支付状态查找订单
	 */
	List<Order> getOrderByPayStatus(String mqID, String payStatus, String requestID);
	
	/**
	 * 根据退款状态查找订单
	 */
	List<Order> getOrderByReturnMoneyStatus(String mqID, String returnMoneyStatus, String requestID);
	
	/**
	 * 根据退货状态查找订单
	 */
	List<Order> getOrderByReturnGoodsStatus(String mqID, String returnGoodsStatus, String requestID);
	
	/**
	 * 根据发货状态查找订单 
	 */
	List<Order> getOrderByShippingStatus(String mqID, String shippingStatus, String requestID);
	
	/**
	 * 根据评论状态查找订单 
	 */
	List<Order> getOrderByCommentStatus(String mqID, String commentStatus, String requestID);
	
	/**
	 * 根据支付渠道查找订单
	 */
	List<Order> getOrderByPayChannel(String mqID, String channle, String requestID);
	
	/**
	 * 为某个订单设置收货地址
	 */
	Order setReceAdd(String mqID, Order order, String requestID);
	
}
