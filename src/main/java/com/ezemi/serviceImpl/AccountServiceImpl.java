package com.ezemi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.User;
import com.ezemi.exception.CustomerServiceException;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public Boolean registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public User isUserValid(String email, String password) {
		User user = userRepo.isValid(email, password);
		return user;
	}

	
	
	@Override
	public boolean changePassword(String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}
}
