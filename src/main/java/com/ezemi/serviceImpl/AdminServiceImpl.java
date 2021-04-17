package com.ezemi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;
import com.ezemi.repository.AdminRepository;
import com.ezemi.repository.ProductRepository;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;

	@Override
	public List<User> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getNotApprovedUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getApprovedCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateCustomer(int userId) {
		userRepo.approveUser(userId);
	}

	@Override
	public void addProduct(Product product) {
		  productRepo.addOrUpdareAProduct(product);
		
	}

	@Override
	public void addBank(Bank bank) {
		adminRepo.addOrUpdateBank(bank);
	}


	@Override
	public void addOrUpdatecategory(Category ct) {
		adminRepo.addOrUpdatecategory(ct);
	}

	@Override
	public Category getCategoryById(int categoryId) {
		return adminRepo.getCategoryById(categoryId);
		
	}

	@Override
	public void addOrUpdateCardType(CardType cardType) {
		adminRepo.addOrUpdateCardType(cardType);
		
	}

}
