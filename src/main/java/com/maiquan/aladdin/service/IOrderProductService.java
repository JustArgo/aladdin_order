package com.maiquan.aladdin.service;

import java.util.List;

import com.maiquan.aladdin.domain.OrderProduct;

public interface IOrderProductService {

	/**
	 * 新增订单商品对象
	 * @param orderProduct
	 * @param requestID
	 * @return
	 */
	int addOrderProduct(OrderProduct orderProduct, String requestID);
	
	/**
	 * 根据id查找订单商品 
	 * @param orderProductID
	 * @return
	 */
	OrderProduct getOrderProductByID(Integer orderProductID, String requestID);

	/**
	 * 删除订单商品 
	 * @param orderProductID
	 * @param requestID
	 * @return
	 */
	int deleteOrderProduct(Integer orderProductID, String requestID);
	
	/**
	 * 修改订单商品 
	 * @param orderProductID
	 * @param requetsID
	 * @return
	 */
	int updateOrderProduct(OrderProduct orderProduct, String requestID);
	
	/**
	 * 根据orderID查找订单商品
	 * @param orderID
	 * @param requestID
	 * @return
	 */
	List<OrderProduct> getOrderProductsByOrderID(Integer orderID, String requestID);
	
	/**
	 * 根据商品名称查找订单商品
	 * @param productName
	 * @param requestID
	 * @return
	 */
	List<OrderProduct> getOrderProductsByProductName(String productName, String requestID);
	
	/**
	 * 根据供应商名称查找订单商品
	 * @param supName
	 * @param requestID
	 * @return
	 */
	List<OrderProduct> getOrderProductsBySupName(String supName, String requestID);
	
}
