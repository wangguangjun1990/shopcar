package com.wang.mapper;

import com.fc.platform.commons.page.Page;
import com.fc.platform.commons.page.Pageable;
import com.wang.entity.Order;
import com.wang.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

	void insertOrder(Order order);

	void deleteOrderByOrderId(Integer orderId);

	void updateOrder(Order order);

	Page<Order> searchOrderByParams(@Param("map") Map<String, String> map, Pageable pageable);

	List<Order> searchOrderByParams(@Param("map") Map<String, String> map);



} 
