package com.ezemi.service;

import com.ezemi.dto.RegisterDto;
import com.ezemi.dto.Status;
import com.ezemi.entity.User;

public interface AccountService {

	public Status registerUser(RegisterDto user);

	public User isUserValid(String email, String password);

	public boolean changePassword(int userId, String newPassword);

}
