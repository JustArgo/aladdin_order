package com.maiquan.aladdin_order.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.maiquan.aladdin_order.domain.Order;
import com.maiquan.aladdin_order.domain.OrderProduct;
import com.maiquan.aladdin_order.mapper.OrderMapper;
import com.maiquan.aladdin_order.mapper.OrderProductMapper;
import com.maiquan.aladdin_order.service.IOrderService;
import com.maiquan.aladdin_order.util.LogUtil;
import com.maiquan.aladdin_product.domain.Product;
import com.maiquan.aladdin_product.domain.ProductSku;
import com.maiquan.aladdin_product.service.IProductService;
import com.maiquan.aladdin_product.service.IProductSkuService;
import com.maiquan.aladdin_receadd.domain.Address;
import com.maiquan.aladdin_receadd.domain.ReceiveAddress;
import com.maiquan.aladdin_receadd.service.IAddressService;
import com.maiquan.aladdin_receadd.service.IManageReceAddService;
import com.maiquan.aladdin_supplier.domain.Supplier;
import com.maiquan.aladdin_supplier.service.ISupplierService;

public class OrderServiceImpl implements IOrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderProductMapper orderProductMapper;
	
	@Autowired
	private IProductSkuService productSkuService;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private ISupplierService supplierService;
	
	@Autowired
	private IManageReceAddService manageReceAddService;
	
	@Autowired
	private IAddressService addressService;
	
	@Override
	public Order getOrderByID(Integer orderID, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByID", requestID, orderID);
		
		Order order = orderMapper.selectByPrimaryKey(orderID);
		
		LogUtil.logOutput("订单微服务", "getOrderByID", requestID, order);
		
		return order;
	}

	@Override
	public List<Order> getChildOrdersByParentOrderID(Integer orderID, String requestID) {
		
		LogUtil.logInput("订单微服务", "getChildOrdersByParentOrderID", requestID, orderID);
		
		Order order = new Order();
		order.setParentID(orderID);
		List<Order> childOrders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getChildOrdersByParentOrderID", requestID, childOrders);		
		
		return childOrders;
		
	}



	@Override
	public int placeOrder(String mqID, Integer[] skuIDs, Integer[] buyNums, Long[] skuPrices, String requestID) {
		
		LogUtil.logInput("订单微服务", "placeOrder", requestID, mqID, skuIDs, buyNums, skuPrices);
		
		//判断参数是否合法
		if(mqID==null || skuIDs==null || buyNums==null ||requestID==null || skuIDs.length!=buyNums.length || skuIDs.length!=skuPrices.length ){
			return 1;
		}
		
		int parentOrderID = dealWithOrder1(mqID, skuIDs, buyNums, skuPrices, requestID);
		
		LogUtil.logOutput("订单微服务", "placeOrder", requestID, parentOrderID);
		
		return parentOrderID;
	}

	@Override
	public int deleteOrder(Integer orderID, String requestID) {
		
		LogUtil.logInput("订单微服务", "deleteOrder", requestID, orderID);
		
		orderMapper.deleteByPrimaryKey(orderID);
		
		LogUtil.logOutput("订单微服务", "deleteOrder", requestID, "无");
		
		return 0;
	}

	@Override
	public int updateOrder(Order order, String requestID) {
		
		LogUtil.logInput("订单微服务", "updateOrder", requestID, order);
		
		orderMapper.updateByPrimaryKeySelective(order);
		
		LogUtil.logOutput("订单微服务", "updateOrder", requestID, "无");
		
		return 0;
	}

	@Override
	public Order getOrderByOrderCode(String orderCode, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByOrderCode", requestID, orderCode);
		
		Order order = new Order();
		order.setOrderCode(orderCode);
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		order = null;
		
		if(orders.size()>0){
			order =  orders.get(0);
		}
		
		LogUtil.logOutput("订单微服务", "getOrderByOrderCode", requestID, order);
		
		return order;
	}

	@Override
	public List<Order> getOrderByMqID(String mqID, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByMqID", requestID, mqID);
		
		Order order = new Order();
		order.setMqID(mqID);
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByMqID", requestID, orders);
		
		return orders;
	}

	@Override
	public int divideOrder(Order order, String requestID) {
		return 0;
	}

	@Override
	public List<Order> getOrderByOrderStatus(String mqID, String orderStatus, String requestID){
		
		LogUtil.logInput("订单微服务", "getOrderByOrderStatus", requestID, orderStatus);
		
		Order order = new Order();
		order.setOrderStatus(orderStatus);
		if(mqID!=null){
			order.setMqID(mqID);
		}
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByOrderStatus", requestID, orders);
		
		return orders;
	}
	
	@Override
	public List<Order> getOrderByPayStatus(String mqID, String payStatus, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByPayStatus", requestID, payStatus);
		
		Order order = new Order();
		order.setPayStatus(payStatus);
		if(mqID!=null){
			order.setMqID(mqID);
		}
		
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByPayStatus", requestID, orders);
		
		return orders;
	}
	
	@Override
	public List<Order> getOrderByReturnMoneyStatus(String mqID, String returnMoneyStatus, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByReturnMoneyStatus", requestID, mqID, returnMoneyStatus);
		
		Order order = new Order();
		order.setReturnMoneyStatus(returnMoneyStatus);
		if(mqID!=null){
			order.setMqID(mqID);
		}
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByReturnMoneyStatus", requestID, orders);
		
		return orders;
	}

	@Override
	public List<Order> getOrderByReturnGoodsStatus(String mqID, String returnGoodsStatus, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByReturnGoodsStatus", requestID, mqID, returnGoodsStatus);
		
		Order order = new Order();
		order.setReturnGoodsStatus(returnGoodsStatus);
		if(mqID!=null){
			order.setMqID(mqID);
		}
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByReturnGoodsStatus", requestID, orders);
		
		return orders;
	}

	@Override
	public List<Order> getOrderByShippingStatus(String mqID, String shippingStatus, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByShippingStatus", requestID, mqID, shippingStatus);
		
		Order order = new Order();
		order.setShippingStatus(shippingStatus);
		if(mqID!=null){
			order.setMqID(mqID);
		}
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByShippingStatus", requestID, orders);
		
		return orders;
	}

	@Override
	public List<Order> getOrderByCommentStatus(String mqID, String commentStatus, String requestID) {
		
		LogUtil.logInput("订单微服务", "getOrderByCommentStatus", requestID, mqID, commentStatus);
		
		Order order = new Order();
		order.setCommentStatus(commentStatus);
		if(mqID!=null){
			order.setMqID(mqID);
		}
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByCommentStatus", requestID, orders);
		
		return orders;
		
	}

	@Override
	public List<Order> getOrderByPayChannel(String mqID, String payChannel, String requestID){
		
		LogUtil.logInput("订单微服务", "getOrderByPayChannel", requestID, payChannel);
		
		Order order = new Order();
		if(mqID!=null){
			order.setMqID(mqID);
		}
		List<Order> orders = orderMapper.selectByCondition(order);
		
		LogUtil.logOutput("订单微服务", "getOrderByPayChannel", requestID, orders);
		
		return orders;
	}
	
	private int dealWithOrder1(String mqID, Integer[] skuIDs, Integer[] buyNums, Long[] skuPrices, String requestID){
		
		Order parentOrder = new Order();
		List<Order> childOrders = new ArrayList<Order>();
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmddSSS");
		
		parentOrder.setParentID(0);
		parentOrder.setOrderCode(sdf.format(new Date())+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10));
		parentOrder.setOrderStatus("COM");			//订单状态
		parentOrder.setPayStatus("NOT");			//支付状态
		parentOrder.setReturnMoneyStatus("NOT");	//退款状态
		parentOrder.setReturnGoodsStatus("NOT");	//退货状态
		parentOrder.setCommentStatus("NOT");		//评论状态
		parentOrder.setShippingStatus("NOT");		//物流状态
		//parentOrder.setPlatform("");
		parentOrder.setMqID(mqID);					//下单用户ID
		//parentOrder.setInvoiceName("");			//发票抬头
		parentOrder.setCountry("");
		parentOrder.setProvince("");
		parentOrder.setCity("");
		parentOrder.setDistrict("");
		parentOrder.setAddress("");
		parentOrder.setRecName("");
		parentOrder.setRecMobile("");
		parentOrder.setCreateTime(new Date());
		
		//总订单金额 
		Long totalPrice = 0L;
		
		//处理子订单  关于运费  不同的sku有不同的计算过程
		for(int i=0;i<skuIDs.length;i++){
			
			//订单总金额
			totalPrice += skuPrices[i]*buyNums[i];
			
			//找出sku 及其对应的商品 以及供应商
			ProductSku sku = productSkuService.getSkuByID(skuIDs[i],UUID.randomUUID().toString());
			Product product = productService.queryProduct(sku.getProductID(),UUID.randomUUID().toString());
			Supplier supplier = supplierService.getSupplier(product.getSupplyID(),UUID.randomUUID().toString());
System.out.println(product.getSupplyID());
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setID((int)(Math.random()*2147483648L));
			orderProduct.setProductID(product.getID());
			orderProduct.setProductName(product.getProductName());
			orderProduct.setSkuID(skuIDs[i]);
			orderProduct.setBuyNum(buyNums[i]);
			orderProduct.setSkuName("");
			orderProduct.setSupName(supplier.getName());
//			orderProduct.setSkuName();

			
			//针对第一种方案 即 一个OrderProduct对应一个childOrder
			Order childOrder = new Order();
			childOrder.setCountry("中国");
			childOrder.setProvince("广东省");
			childOrder.setCity("广州市");
			childOrder.setDistrict("天河区");
			childOrder.setAddress("棠下");
			
			//设置子订单的状态
			childOrder.setCommentStatus("NOT");
			childOrder.setReturnGoodsStatus("NOT");
			childOrder.setReturnMoneyStatus("NOT");
			childOrder.setPayStatus("NOT");
			childOrder.setShippingStatus("NOT");
			childOrder.setOrderStatus("COM");
			
			//设置收货相关
				//根据mqID 得到默认收货地址
			ReceiveAddress receiveAddress = manageReceAddService.getDefaultAddress(mqID, requestID);
			if(receiveAddress!=null){
				
				childOrder.setRecName(receiveAddress.getRecName());
				childOrder.setRecMobile(receiveAddress.getRecMobile());
				childOrder.setAddress(receiveAddress.getAddress());
				
				Address province = null;
				Address city 	 = null;
				Address district = null;
				
				if(receiveAddress.getProvinceID()!=null){
					province = addressService.getAddress(Integer.valueOf(receiveAddress.getProvinceID()),UUID.randomUUID().toString());
				}
				if(receiveAddress.getCityID()!=null){
					city = addressService.getAddress(Integer.valueOf(receiveAddress.getCityID()),UUID.randomUUID().toString());
				}
				if(receiveAddress.getDistrictID()!=null){
					district = addressService.getAddress(Integer.valueOf(receiveAddress.getDistrictID()),UUID.randomUUID().toString());
				}
				
				if(province!=null){
					childOrder.setProvince(province.getName());
				}
				if(city!=null){
					childOrder.setCity(city.getName());
				}
				if(district!=null){
					childOrder.setDistrict(district.getName());
				}
				
			}
			
			childOrder.setPostFee(8L);
			childOrder.setOrderSum(skuPrices[i]*buyNums[i]);
			childOrder.setMqID(mqID);
//支付完成之后才有发票
//			childOrder.setInvoiceName("maiquan");
			childOrder.setPlatform("APP");
			childOrder.setCreateTime(new Date());
			
			childOrder.setOrderCode(sdf.format(new Date())+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10));
			childOrder.setParentCode(parentOrder.getOrderCode());
			
			orderProducts.add(orderProduct);
			childOrders.add(childOrder);
			
		}
		
		// 设置parentOrder的总订单金额
		parentOrder.setpSum(totalPrice);
		
		// 插入parentOrder
		orderMapper.insert(parentOrder);
		System.out.println("parentOrder的id：--"+parentOrder.getID());
		
		// 获得parentOrder的id 设置childOrder的id 并插入 然后再设置 orderProduct的orderID
		for(int i=0;i<childOrders.size();i++){
			Order childOrder = childOrders.get(i);
			childOrder.setParentID(parentOrder.getID());
			orderMapper.insert(childOrder);
			
			orderProducts.get(i).setOrderID(childOrder.getID());
			orderProductMapper.insert(orderProducts.get(i));
			
		}
		
		return parentOrder.getID();
	}
	
	public int dealWithOrder2(String mqID, Integer[] skuIDs, Integer[] buyNums, Long[] skuPrices, String requestID){
		
		Order parentOrder = new Order();
		List<Order> childOrders = new ArrayList<Order>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmddSSS");
		//用于存放 相同供应商相同仓库的 订单商品对象
		Map<String,List<OrderProduct>> supplyHouseMap = new HashMap<String,List<OrderProduct>>(); 
		
		//用于 临时 存放 skuID和 skuPrice 的对应关系
		Map<Integer,Long> skuPriceMap = new HashMap<Integer,Long>();
		
		parentOrder.setParentID(0);
		parentOrder.setOrderCode(sdf.format(new Date())+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10));
		parentOrder.setOrderStatus("COM");			//订单状态
		parentOrder.setPayStatus("NOT");			//支付状态
		parentOrder.setReturnMoneyStatus("NOT");	//退款状态
		parentOrder.setReturnGoodsStatus("NOT");	//退货状态
		parentOrder.setCommentStatus("NOT");		//评论状态
		parentOrder.setShippingStatus("NOT");		//物流状态
		//parentOrder.setPlatform("");
		parentOrder.setMqID(mqID);					//下单用户ID
		//parentOrder.setInvoiceName("");			//发票抬头
		parentOrder.setCountry("");
		parentOrder.setProvince("");
		parentOrder.setCity("");
		parentOrder.setDistrict("");
		parentOrder.setAddress("");
		parentOrder.setRecName("");
		parentOrder.setRecMobile("");
		parentOrder.setCreateTime(new Date());
		
		//总订单金额 
		Long totalPrice = 0L;
		
		//处理子订单  关于运费  不同的sku有不同的计算过程
		for(int i=0;i<skuIDs.length;i++){
			
			//订单总金额
			totalPrice += skuPrices[i]*buyNums[i];
			
			//找出sku 及其对应的商品 以及供应商
			ProductSku sku = productSkuService.getSkuByID(skuIDs[i],UUID.randomUUID().toString());
			Product product = productService.queryProduct(sku.getProductID(),UUID.randomUUID().toString());
			Supplier supplier = supplierService.getSupplier(product.getSupplyID(),UUID.randomUUID().toString());
System.out.println(product.getSupplyID());
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setID((int)(Math.random()*2147483648L));
			orderProduct.setProductID(product.getID());
			orderProduct.setProductName(product.getProductName());
			orderProduct.setSkuID(skuIDs[i]);
			orderProduct.setSkuName("");
			orderProduct.setSupName(supplier.getName());
//			orderProduct.setSkuName();
			
			
			if(supplyHouseMap.containsKey(product.getSupplyID()+":"+sku.getHouseID())){
				supplyHouseMap.get(product.getSupplyID()+":"+sku.getHouseID()).add(orderProduct);
			}else{
				List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
				orderProducts.add(orderProduct);
				supplyHouseMap.put(product.getSupplyID()+":"+sku.getHouseID(), orderProducts);
			}
			
			//针对第一种方案 即 一个OrderProduct对应一个childOrder
			Order childOrder = new Order();
			childOrder.setCountry("中国");
			childOrder.setProvince("广东省");
			childOrder.setCity("广州市");
			childOrder.setDistrict("天河区");
			childOrder.setAddress("棠下");
			
			//设置子订单的状态
			childOrder.setCommentStatus("NOT");
			childOrder.setReturnGoodsStatus("NOT");
			childOrder.setReturnMoneyStatus("NOT");
			childOrder.setPayStatus("NOT");
			childOrder.setShippingStatus("NOT");
			childOrder.setOrderStatus("COM");
			
			//设置收货相关
			childOrder.setRecName("老谭");
			childOrder.setRecMobile("234");
			
			childOrder.setPostFee(8L);
			childOrder.setOrderSum(sku.getSkuPrice()*orderProduct.getBuyNum());
			childOrder.setMqID(mqID);
			childOrder.setInvoiceName("maiquan");
			childOrder.setPlatform("APP");
			childOrder.setCreateTime(new Date());
			
		}
		
		parentOrder.setpSum(totalPrice);				//整个父订单的总金额
		//插入父订单 并返回ID
		orderMapper.insert(parentOrder);
		
System.out.println("------------"+parentOrder.getID());
		
		//逐个设置子订单 并设置子订单
		for(int i=0;i<childOrders.size();i++){
			childOrders.get(i).setParentID(parentOrder.getID());
			childOrders.get(i).setParentCode(parentOrder.getOrderCode());
			orderMapper.insert(childOrders.get(i));
		}
		
		//以下for循环 基于  同一供应商同一货仓 此时一个子订单可以对应多个 订单商品
		for(Entry<String,List<OrderProduct>> entry:supplyHouseMap.entrySet()){
			
			List<OrderProduct> orderProducts = entry.getValue();
			
			Order childOrder = new Order();
			childOrder.setParentID(parentOrder.getID());
			childOrder.setParentCode(parentOrder.getOrderCode());
			
			//设置订单的各项状态   由于同一个供应商 的同一个货仓 可能有 多个订单商品  所以 有些状态无法设置
			childOrder.setOrderStatus("COM");			//订单状态
			childOrder.setPayStatus("NOT");				//支付状态
			childOrder.setReturnMoneyStatus("NOT");		//退款状态
			childOrder.setReturnGoodsStatus("NOT");		//退货状态
			childOrder.setCommentStatus("NOT");			//评论状态
			childOrder.setShippingStatus("NOT");		//物流状态
			
			//设置订单号
			childOrder.setOrderCode(sdf.format(new Date())+(int)(Math.random()*10)+(int)(Math.random()*10)+(int)(Math.random()*10));
			
			//子订单 也要 设置收货地址
			childOrder.setCountry("");
			childOrder.setProvince("");
			childOrder.setCity("");
			childOrder.setDistrict("");
			childOrder.setAddress("");
			
			//设置创建时间
			childOrder.setCreateTime(new Date());
			
			//设置发票抬头
//			childOrder.setInvoiceName("");
			
			//设置下单用户的ID
			childOrder.setMqID(mqID);
			
			childOrder.setRecName("");
			childOrder.setRecMobile("");

			Long childOrderPostFee = 0L;
			Long childOrderSum = 0L;			//子订单金额
			//将子订单对应的订单商品 合并处理 设置  运费,子订单金额
			for(int i=0;i<orderProducts.size();i++){
				OrderProduct orderProduct = orderProducts.get(i);
				
				childOrderSum += skuPriceMap.get(orderProduct.getSkuID())*orderProduct.getBuyNum();
				
			}
			
//			childOrder.setPostFee(32L);  子订单的运费处理有问题
//			childOrder.setPlatform("");
			
			//假设子订单处理好了
			orderMapper.insert(childOrder);

			for(int i=0;i<orderProducts.size();i++){
				orderProducts.get(i).setOrderID(childOrder.getID());
				orderProductMapper.insert(orderProducts.get(i));
			}
			
			
		}
		
		return 1;
	}
	
}
