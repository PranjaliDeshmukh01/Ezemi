package com.ezemi.service;

import java.util.List;

import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.EmiType;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;

public interface AdminService {

	List<User> getAllCustomers();
	
	List<User> getNotApprovedUsers();
	
	List<User> getApprovedCustomers();
	
	void activateCustomer();
	
	void addProduct(Product product);
	
	void addOrUpdatecategory(Category ct);
	
	Category getCategoryById(int categoryId);
	
	Bank addBank(Bank bank);
	
	EmiType addEmiType(EmiType emiType);
	
	//CardType
	void addOrUpdateCardType(CardType cardType);
	
}
