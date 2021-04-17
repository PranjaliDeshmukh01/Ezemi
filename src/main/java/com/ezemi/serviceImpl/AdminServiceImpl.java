package com.ezemi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ezemi.entity.Bank;
import com.ezemi.entity.EmiType;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;
import com.ezemi.repository.AdminRepository;
import com.ezemi.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepo;

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
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
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

}
