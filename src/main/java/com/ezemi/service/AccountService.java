package com.ezemi.service;

import java.util.List;

import com.ezemi.dto.RegisterDto;
import com.ezemi.dto.Status;
import com.ezemi.entity.Bank;
import com.ezemi.entity.User;

public interface AccountService {

	public Status registerUser(RegisterDto user);

	public User isUserValid(String email, String password);

	public Status changePassword(String email, String newPassword);

	public List<Bank> getAllBanks();
	
	public boolean userExists(String email);
}
