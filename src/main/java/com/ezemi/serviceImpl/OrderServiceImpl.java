package com.ezemi.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.Order;
import com.ezemi.entity.Product;
import com.ezemi.repository.OrderRepository;
import com.ezemi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepo;

	@Override
	public void makeAnOrder(int userId, int emiTypeId, Product product) {

	}

	
	
	@Override
	public List<Order> getOrdersByUserId(int userId) {
		return orderRepo.getOrdersByUserId(userId);
	}
	
	

	@Override
	public List<Order> getNotFullyPaidOrders(int userId) {
		return orderRepo.getOrdersByUserId(userId).stream().filter(x -> x.getAmountDue() > 0)
				.collect(Collectors.toList());
	}
	
	

	@Override
	public List<Order> getFullyPaidOrders(int userId) {
		return orderRepo.getOrdersByUserId(userId).stream().filter(x -> x.getAmountDue() == 0)
				.collect(Collectors.toList());
	}

}
