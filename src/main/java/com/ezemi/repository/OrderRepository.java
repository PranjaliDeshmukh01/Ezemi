package com.ezemi.repository;

import java.util.List;

import com.ezemi.entity.Order;

public interface OrderRepository {

	public void addAnOrder(Order order);
	
	public void updateInstallment(int orderId);
	
	public List<Order> getOrdersByProductId(int productId);
	
	public void shipOrder(int orderId);
	
	public List<Order> getOrdersByUserId(int userId);
	
}
