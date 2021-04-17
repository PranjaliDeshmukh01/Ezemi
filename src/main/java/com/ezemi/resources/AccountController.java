package com.ezemi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.LoginDto;
import com.ezemi.dto.LoginStatusDto;
import com.ezemi.dto.RegisterDto;
import com.ezemi.dto.RoleType;
import com.ezemi.dto.Status;
import com.ezemi.entity.User;
import com.ezemi.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	
	@PostMapping("/register")
	public Status register(@RequestBody RegisterDto regdto) {
		return accountService.registerUser(regdto);
	}
	
	
	
	
	@PostMapping("/login")
	public LoginStatusDto login(@RequestBody LoginDto logindto) {
		User user = accountService.isUserValid(logindto.getEmailId(), logindto.getPassword());
		LoginStatusDto lsd = new LoginStatusDto();
		if(user==null) {
			lsd.setStatusMsg("FAILURE");
			return lsd;
		} 
		else if(user.getRole() == RoleType.Customer) {
			lsd.setRole("CUSTOMER");
			if(!user.getIsApproved()) {
				lsd.setStatusMsg("FAILURE");
				return lsd;
			} 
			else {
				lsd.setUserId(user.getUserId());
				lsd.setEmail(user.getEmail());
				lsd.setName(user.getFirstname());
				lsd.setStatusMsg("SUCCESS");
				return lsd;
			}
		}
		else {
			lsd.setRole("ADMIN");
			lsd.setUserId(user.getUserId());
			lsd.setEmail(user.getEmail());
			lsd.setName(user.getFirstname());
			lsd.setStatusMsg("SUCCESS");
			return lsd;
		}
	}
}
