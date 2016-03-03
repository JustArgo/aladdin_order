package com.maiquan.aladdin.mapper;

import java.util.List;

import com.maiquan.aladdin.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer orderID);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer orderID);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    List<Order> selectByCondition(Order order);
}