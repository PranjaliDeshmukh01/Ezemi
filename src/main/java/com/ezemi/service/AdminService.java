package com.ezemi.service;

import java.util.List;

import com.ezemi.dto.Status;
import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.ContactUs;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;

public interface AdminService {

	List<User> getAllCustomers();
	
	List<User> getNotApprovedUsers();
	
	List<User> getApprovedCustomers();
	
	void activateCustomer(int userId);
	
	void addProduct(Product product);
	
	void addBank(Bank bank);
	
	void addOrUpdatecategory(Category ct);
	
	Category getCategoryById(int categoryId);
	
	void addOrUpdateCardType(CardType cardType);

	
	public Status addAdmin(String name,String email, String password);
	

	
	List<Bank> getallBanks();
	
	List<CardType> getAllCardType();
	
	void deleteACategory(int categoryId);
	
	void updateCategoryDetails(Category category);
	
	void addCustomerQuery(ContactUs query);
	
}
