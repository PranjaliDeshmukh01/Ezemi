package com.ezemi.serviceImpl;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.dto.RegisterDto;
import com.ezemi.dto.RoleType;
import com.ezemi.dto.Status;
import com.ezemi.dto.Status.StatusType;
import com.ezemi.entity.Address;
import com.ezemi.entity.BankDetails;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.User;
import com.ezemi.repository.AdminRepository;
import com.ezemi.repository.BankRepository;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.AccountService;
import com.ezemi.service.EmailService;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	BankRepository bankRepo;
	
	@Autowired
	EmailService emailService;
	
	private String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	@Override
	public Status registerUser(RegisterDto userdto) {
		if(!userRepo.userExists(userdto.getEmail())) {
			
			User user = new User();
			
			user.setFirstname(userdto.getFirstname());
			user.setLastname(userdto.getLastname());
			user.setPassword(encryptPassword(userdto.getPassword()));
			user.setEmail(userdto.getEmail());
			user.setPhoneNo(userdto.getPhoneNo());
			user.setCompanyName(userdto.getCompanyName());
			user.setCompanyAddress(userdto.getCompanyAddress());
			user.setDesignation(userdto.getDesignation());
			user.setPanCardNo(userdto.getPanCardNo());
			user.setIsApproved(false);
			user.setRole(RoleType.Customer);
			
			BankDetails bankdetails = new BankDetails();
			bankdetails.setBank(bankRepo.getBankById(userdto.getBankId()));
			bankdetails.setIFSC_code(userdto.getIfsc_code());
			bankdetails.setAccount_number(userdto.getAccount_number());
			bankdetails.setUser(user);
			
			Address address = new Address();
			address.setAddressLine1(userdto.getAddressLine1());
			address.setAddressLine2(userdto.getAddressLine2());
			address.setCity(userdto.getCity());
			address.setPinCode(userdto.getPinCode());
			address.setUser(user);
			
			user.setBankDetails(bankdetails);
			user.setAddress(address);
			
			
			EmiCard card = new EmiCard();
			card.setCardType(adminRepo.getCardTypeById(userdto.getCardTypeId()));
			card.setCredit(card.getCardType().getCreditAmount());
			card.setCreditLeft(card.getCredit());
			card.setIsActivated(false);
			
			card.setUser(user);
			user.setCard(card);
		
			userRepo.registerorUpdateUser(user);
			String subject = "Registeration confirmation";
			String text=user.getFirstname()+" you have successfully registered.";
			emailService.sendEmail(user.getEmail(),text,subject);
			
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("User registered successfully!");
			return status;
		}
		else {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage("User already exists!");
			return status;
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
