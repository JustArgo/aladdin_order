package com.maiquan.aladdin_order.mapper;

import java.util.List;

import com.maiquan.aladdin_order.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectByCondition(Order order);
    
}