package com.ezemi.repository;

import java.util.List;

import com.ezemi.entity.Address;
import com.ezemi.entity.Bank;
import com.ezemi.entity.BankDetails;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.Order;
import com.ezemi.entity.User;

public interface UserRepository {
	
	public void registerorUpdateUser(User user); 
	public User getUserById(int userId); 
	
	public void changePassword(int userId,String password);
	
	public void addOrUpdateAddress(Address address, int userId);
	
	public void addOrUpdateBankdetails(BankDetails details, int userId);
	
	public void approveUser(int userId);	
	
	public User getUserByEmail(String email);
	
	public void deleteUserById(int userId);
	
	public List<User> getAllCustomers(); 
	
	public List<User> getAllUsersByCardTypeId(int cardTypeid);
	
	public List<User> getUsersByName(String uname);
	
	public void activateCard(String emiCardNo);
	
	User isValid(String userEmail, String userPassword);
	
	List<User> getNotApprovedCustomers();
	
	List<User> getApprovedCustomers();
	
	public EmiCard getCardByUserId(int userId);
	
	
}
