package com.taotao.portal.service;

import com.taotao.portal.pojo.Order;

public interface OrderService {
/***
 * 提交订单
 * @param order   订单信息对象
 * @return  返回订单号
 */
	String createOrder(Order order);

}
