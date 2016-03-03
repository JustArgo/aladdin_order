package com.maiquan.aladdin.mapper;

import java.util.List;

import com.maiquan.aladdin.domain.OrderProduct;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
    
    List<OrderProduct> selectByCondition(OrderProduct orderProduct);
}