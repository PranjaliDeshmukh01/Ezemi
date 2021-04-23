package com.ezemi.service;

import java.util.List;

import com.ezemi.entity.Order;
import com.ezemi.entity.User;

public interface OrderService {
	
	public void makeAnOrder(int userId, int emiMonths, int productId, boolean autodebit);

	public List<Order> getOrdersByUserId(int userId);

	public List<Order> getNotFullyPaidOrders(int userId);

	public List<Order> getFullyPaidOrders(int userId);
	
	public Order getOrderById(int orderId);
	
	public void payEmi(int orderId);
	
	public List<Order> getAllOrders();
	
	public void shipOrder(int orderId);
	
	public User getUserByOrderId(int orderId);
}
