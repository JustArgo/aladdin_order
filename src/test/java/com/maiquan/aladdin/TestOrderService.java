package com.maiquan.aladdin;

import java.util.Date;
import java.util.UUID;

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
		order.setOrderCode(UUID.randomUUID().toString());
		order.setOrderID(1);
		order.setOrderPrice(1309L);
		order.setOrderStatus("DEL");
		order.setParentID(3);
		order.setPayChannel(Order.PAY_CHANNEL_ALIPAY);
		order.setPayPrice(1309L);
		order.setPayStatus("payed");
		order.setPayTime(new Date());
		order.setPostFee(99L);
		order.setProvince("guangdong");
		order.setRecMobile("13456989856");
		order.setRecName("jiangdong");
		
		orderService.addOrder(order, UUID.randomUUID().toString());
		
	}
	 
	
	
}
