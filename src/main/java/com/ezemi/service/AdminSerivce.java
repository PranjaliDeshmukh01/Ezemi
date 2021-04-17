package com.ezemi.service;

import java.util.List;

import com.ezemi.entity.Bank;
import com.ezemi.entity.EmiType;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;

public interface AdminSerivce {

	List<User> getAllCustomers();
	
	List<User> getNotApprovedUsers();
	
	List<User> getApprovedCustomers();
	
	void activateCustomer();
	
	Product addProduct(Product product);
	
	Bank addBank(Bank bank);
	
	EmiType addEmiType(EmiType emiType);
	
	
}
