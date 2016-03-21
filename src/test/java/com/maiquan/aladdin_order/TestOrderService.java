package com.maiquan.aladdin_order;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.maiquan.aladdin_order.domain.Order;
import com.maiquan.aladdin_order.service.IOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestOrderService {
	
	@Autowired
	private IOrderService orderService;
	
	/**
	 * 测试增加订单
	 */
	@Test
	public void testplaceOrder(){
		
		Order order = new Order();
		order.setAddress("棠东");
		order.setCity("biejing");
		order.setConfirmTime(new Date());
		order.setCountry("China");
		order.setCreateTime(new Date());
		order.setDistrict("广州");
		order.setInvoiceName("麦圈");
		order.setMqID("33");
		//order.setID((int)(Math.random()*2147483648L));
		order.setOrderCode(UUID.randomUUID().toString().replace("-", ""));
		order.setOrderStatus("DEL");
		order.setParentID(3);
		order.setpSum(33l);
		order.setPayStatus("pay");
		order.setPayTime(new Date());
		order.setPostFee(99L);
		order.setProvince("guangdong");
		order.setRecMobile("13456989856");
		order.setRecName("jiangdong");
		
		//orderService.placeOrder("mqsdfwerewr234234",new Integer[]{1,2,3},new Integer[]{1,1,1},new Long[]{115L,220L,30000L}, UUID.randomUUID().toString());
		
		Order retOrder = orderService.getOrderByID(order.getID(), UUID.randomUUID().toString());
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
		order.setID(1);
		order.setOrderSum(666L);
		
		orderService.updateOrder(order, UUID.randomUUID().toString());
		
		Order retOrder = orderService.getOrderByID(1, UUID.randomUUID().toString());
		
		Assert.assertEquals(666, retOrder.getOrderSum().intValue());
		
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

		List<Order> orders = orderService.getOrderByOrderStatus(null, "DEL", UUID.randomUUID().toString());
		Assert.assertEquals(1, orders.size());
		
	}
	
	
	/**
	 * 测试根据支付状态 查找订单
	 */
	@Test
	public void testGetOrderByPayStatus(){
		
		List<Order> orders = orderService.getOrderByPayStatus(null, "pay", UUID.randomUUID().toString());
		Assert.assertEquals(1, orders.size());
		
	}
	
	/**
	 * 测试根据支付渠道 查找订单
	 */
	@Test
	public void testGetOrderByPayChannel(){
		
	}
	
	
	
}
