package com.ezemi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.dto.LoginDto;
import com.ezemi.dto.LoginStatusDto;
import com.ezemi.dto.RegAdminDto;
import com.ezemi.dto.RegisterDto;
import com.ezemi.dto.RoleType;
import com.ezemi.dto.Status;
import com.ezemi.entity.Bank;
import com.ezemi.entity.User;
import com.ezemi.helper.RandomPinGenerator;
import com.ezemi.service.AccountService;
import com.ezemi.service.EmailService;

@RestController
@CrossOrigin
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmailService emailService;

	
	@PostMapping("/register")
	public Status register(@ModelAttribute RegisterDto regdto) {
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
				lsd.setCardType(user.getCard().getCardType().getCardTypeId());
				lsd.setCeditLeft(user.getCard().getCreditLeft());
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
	
	@GetMapping("/getallbanks")
	public List<Bank> getOtp() {
		return accountService.getAllBanks();
	}
	
	
	@GetMapping("/getotp")
	public String getOtp(@RequestParam("emailId") String emailId) {
		String otp = RandomPinGenerator.generate4digitPin();
		String subject = "Otp Verification";
		String text = "Your otp for EzEmi registeration is "+ otp+". \n Do not share this otp. Ignore if not requested by you.";
		emailService.sendEmail(emailId, text, subject);
		return otp;
	}
}
