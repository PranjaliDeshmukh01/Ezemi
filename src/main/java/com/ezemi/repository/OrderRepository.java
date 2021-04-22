package com.ezemi.repository;

import java.util.List;

import com.ezemi.entity.Order;

public interface OrderRepository {

	public void addOrUpdateOrder(Order order);
	
	public Order getOrderById(int orderId);
	
	public void updateInstallment(int orderId);
	
	public List<Order> getOrdersByProductId(int productId);
	
	public void shipOrder(int orderId);
	
	public List<Order> getOrdersByUserId(int userId);
	
}
