package com.ezemi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.EmiType;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;
import com.ezemi.repository.AdminRepository;
import com.ezemi.repository.ProductRepository;

import com.ezemi.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
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
	public void activateCustomer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProduct(Product product) {
		  productRepo.addOrUpdareAProduct(product);
		
	}

	@Override
	public Bank addBank(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmiType addEmiType(EmiType emiType) {
		// TODO Auto-generated method stub
		return null;
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
