package com.ezemi.service;

import java.util.List;

import com.ezemi.entity.Order;
import com.ezemi.entity.Product;

public interface OrderService {
	
	public void makeAnOrder(int userId, int emiTypeId, Product product);

	public List<Order> getOrdersByUserId(int userId);

	public List<Order> getNotFullyPaidOrders(int userId);

	public List<Order> getFullyPaidOrders(int userId);
}
