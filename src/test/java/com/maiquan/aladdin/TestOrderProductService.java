package com.maiquan.aladdin;

import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maiquan.aladdin.domain.OrderProduct;
import com.maiquan.aladdin.service.IOrderProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:local/applicationContext.xml")
public class TestOrderProductService {

	@Autowired
	private IOrderProductService orderProductService;
	
	/**
	 * 测试新增 订单商品对象
	 */
	
	public void testAddOrderProduct(){
		
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setID(1);
		orderProduct.setOrderID("1");
		orderProduct.setProduct(1);
		orderProduct.setProductName("羽绒");
		orderProduct.setSkuID(13);
		orderProduct.setSkuName("蓝色XL");
		orderProduct.setSupName("alibaba");
		
		orderProductService.addOrderProduct(orderProduct, UUID.randomUUID().toString());
		
		OrderProduct retOrderProduct = orderProductService.getOrderProductByID(1, UUID.randomUUID().toString());
		Assert.assertNotNull(retOrderProduct);
		
	}
	
	/**
	 * 测试删除订单商品对象
	 */
	
	public void testDeleteOrderProduct(){
		
		orderProductService.deleteOrderProduct(1, UUID.randomUUID().toString());
		OrderProduct orderProduct = orderProductService.getOrderProductByID(1, UUID.randomUUID().toString());
		Assert.assertNull(orderProduct);
		
	}
	
	/**
	 * 测试修改订单商品
	 */
	
	public void testUpdateOrderProduct(){
		
		OrderProduct orderProduct = new OrderProduct();
		orderProduct.setID(1);
		orderProduct.setProductName("yurong");
		
		orderProductService.updateOrderProduct(orderProduct, UUID.randomUUID().toString());
		OrderProduct retOrderProduct = orderProductService.getOrderProductByID(1, UUID.randomUUID().toString());
		Assert.assertEquals("yurong", retOrderProduct.getProductName());
	}
	
	@Test
	public void testGetOrderProductByOrderID(){
		
		List<OrderProduct> orderProducts = orderProductService.getOrderProductsByOrderID(1, "");
		Assert.assertEquals(1, orderProducts.size());
	}
	
	@Test
	public void testGetOrderProductByProductName(){
		
		List<OrderProduct> orderProducts = orderProductService.getOrderProductsByProductName("yurong", "");
		Assert.assertEquals(1, orderProducts.size());
		
	}
	
	@Test
	public void testGetOrderProductBySupName(){
		
		List<OrderProduct> orderProducts = orderProductService.getOrderProductsBySupName("alibaba", "");
		Assert.assertEquals(1, orderProducts.size());
		
	}
	
}
