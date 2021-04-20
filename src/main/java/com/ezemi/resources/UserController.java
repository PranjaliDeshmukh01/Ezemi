package com.ezemi.resources;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.Status;
import com.ezemi.entity.Product;

@RestController
@CrossOrigin
public class UserController {

	@PostMapping(value="/activatecard")
	public Status activateCard(int userId) {
		
		return null;
	}
	
}
