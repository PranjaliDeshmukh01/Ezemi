package com.ezemi.serviceImpl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.User;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.AccountService;
import com.ezemi.service.EmailService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	EmailService emailService;
	
	private String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	@Override
	public Boolean registerUser(User user) {
		if(!userRepo.userExists(user.getEmail())) {
			user.setPassword(encryptPassword(user.getPassword()));
			
			userRepo.registerorUpdateUser(user);
			String subject = "Registeration confirmation";
			String text=user.getFirstname()+" you have successfully registered.";
			emailService.sendEmail(user.getEmail(),text,subject);
			return true;
		}
		else {
			return false;
		}
	}
	
	

	@Override
	public User isUserValid(String email, String password) {
		User user = userRepo.getUserByEmail(email);
		if(BCrypt.checkpw(password, user.getPassword())) {
			return user;
		}
		else {
			return null;
		}	
	}

	
	
	@Override
	public boolean changePassword(int userId, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}
}
