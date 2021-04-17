package com.ezemi.service;

import com.ezemi.entity.User;

public interface AccountService {

	public Boolean registerUser(User user);

	public User isUserValid(String email, String password);

	public boolean changePassword(String newPassword);

}
