package com.maiquan.aladdin;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maiquan.aladdin.domain.Order;
import com.maiquan.aladdin.service.IOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:local/applicationContext.xml")
public class TestOrderService {
	
	@Autowired
	private IOrderService orderService;
	
	/**
	 * 测试增加订单
	 */
	@Test
	public void testAddOrder(){
		
		Order order = new Order();
		order.setAddress("棠东");
		order.setCity("biejing");
		order.setConfirmTime(new Date());
		order.setCountry("China");
		order.setCreateTime(new Date());
		order.setDistrict("广州");
		order.setInvoiceName("麦圈");
		order.setMqID("33");
		order.setOrderCode(UUID.randomUUID().toString().replace("-", ""));
		order.setOrderID(1);
		order.setOrderPrice(1309L);
		order.setOrderStatus("DEL");
		order.setParentID(3);
		order.setPayChannel(Order.PAY_CHANNEL_ALIPAY);
		order.setPayPrice(1309L);
		order.setPayStatus("pay");
		order.setPayTime(new Date());
		order.setPostFee(99L);
		order.setProvince("guangdong");
		order.setRecMobile("13456989856");
		order.setRecName("jiangdong");
		
		//orderService.addOrder(order, UUID.randomUUID().toString());
		
		Order retOrder = orderService.getOrderByID(order.getOrderID(), UUID.randomUUID().toString());
		Assert.assertNotNull(retOrder);
		
	}

	/**
	 * 测试删除订单
	 */
	@Test
	public void testDeleteOrder(){
		
		orderService.deleteOrder(1, UUID.randomUUID().toString());
		Order order = orderService.getOrderByID(1, UUID.randomUUID().toString());
		Assert.assertNull(order);
		
	}
	
	/**
	 * 测试更新订单
	 */
	@Test
	public void testUpdateOrder(){
		
		Order order = new Order();
		order.setOrderID(1);
		order.setOrderPrice(666L);
		
		orderService.updateOrder(order, UUID.randomUUID().toString());
		
		Order retOrder = orderService.getOrderByID(1, UUID.randomUUID().toString());
		
		Assert.assertEquals(666, retOrder.getOrderPrice().intValue());
		
	}
	
	/**
	 * 测试根据订单号 查找订单
	 */
	@Test
	public void testGetOrderByOrderCode(){
		
		Order order = orderService.getOrderByOrderCode("434b2e99479241fab20c8b3fc569a7f2", UUID.randomUUID().toString());
		Assert.assertNotNull(order);
	}
	
	/**
	 * 测试根据下单用户的id 查找订单
	 */
	@Test
	public void testGetOrderByMqID(){
		
		List<Order> orders = orderService.getOrderByMqID("33",UUID.randomUUID().toString());
		Assert.assertEquals(1, orders.size());
		
	}
	
	/**
	 * 测试根据订单状态 查找订单
	 */
	@Test
	public void testGetOrderByOrderStatus(){

		List<Order> orders = orderService.getOrderByOrderStatus("DEL", UUID.randomUUID().toString());
		Assert.assertEquals(1, orders.size());
		
	}
	
	
	/**
	 * 测试根据支付状态 查找订单
	 */
	@Test
	public void testGetOrderByPayStatus(){
		
		List<Order> orders = orderService.getOrderByPayStatus("pay", UUID.randomUUID().toString());
		Assert.assertEquals(1, orders.size());
		
	}
	
	/**
	 * 测试根据支付渠道 查找订单
	 */
	@Test
	public void testGetOrderByPayChannel(){
		
		List<Order> orders = orderService.getOrderByPayChannel(Order.PAY_CHANNEL_ALIPAY, UUID.randomUUID().toString());
		Assert.assertEquals(1, orders.size());
		
	}
	
	
	
}
