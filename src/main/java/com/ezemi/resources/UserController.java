package com.ezemi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.Status;
import com.ezemi.entity.Address;
import com.ezemi.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	UserService userSerive;

	@PostMapping(value="/activatecard")
	public Status activateCard(int userId) {
		
		return null;
	}
		
//	@GetMapping(value="/getAddressByUserId")
//	public Address getAddressByUserId(int userId) {
//		
//	}
	
	
	
}
