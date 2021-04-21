package com.ezemi.serviceImpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.dto.RoleType;
import com.ezemi.dto.Status;
import com.ezemi.dto.Status.StatusType;
import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.entity.User;
import com.ezemi.repository.AdminRepository;
import com.ezemi.repository.BankRepository;
import com.ezemi.repository.ProductRepository;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.AdminService;
import com.ezemi.service.EmailService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository adminRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	EmailService emailServie;
	
	@Autowired
	BankRepository bankRepo;
	
	private String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	


	@Override
	public List<User> getAllCustomers() {
		return userRepo.getAllCustomers();
	}

	@Override
	public List<User> getNotApprovedUsers() {
		return userRepo.getNotApprovedCustomers();
	}

	@Override
	public List<User> getApprovedCustomers() {
		return userRepo.getApprovedCustomers();
	}

	@Override
	public void activateCustomer(int userId) {
		userRepo.approveUser(userId);	
	}

	@Override
	public void addProduct(Product product) {
		  productRepo.addOrUpdareAProduct(product);
		
	}

	@Override
	public void addBank(Bank bank) {
		adminRepo.addOrUpdateBank(bank);
	}


	@Override
	public void addOrUpdatecategory(Category ct) {
		adminRepo.addOrUpdatecategory(ct);
	}

	@Override
	public Category getCategoryById(int categoryId) {
		return adminRepo.getCategoryById(categoryId);
		
	}

	@Override
	public void addOrUpdateCardType(CardType cardType) {
		adminRepo.addOrUpdateCardType(cardType);
		
	}

	@Override
	public Status addAdmin(String name, String email, String password) {
		Status status = new Status();

		if (!userRepo.userExists(email)) {
			User user = new User();
			user.setEmail(email);
			password = encryptPassword(password);
			user.setPassword(password);
			user.setRole(RoleType.Admin);
			user.setFirstname(name);
			user.setIsApproved(true);
			userRepo.registerorUpdateUser(user);
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Admin added");
		}

		else {
			status.setStatus(StatusType.FAILURE);
			status.setMessage("Email exists");
		}
		return status;
	}

	public List<Bank> getallBanks() {
		return bankRepo.getAllBanks();
	}

	
	@Override
	public List<CardType> getAllCardType() {
		return adminRepo.getAllCardTypes();
	}

	@Override
	public void deleteACategory(int categoryId) {
		adminRepo.deleteACategory(categoryId);
		
	}




	@Override
	public void updateCategoryDetails(Category category) {
		adminRepo.updateCategoryDetails(category);
		
	}



}
