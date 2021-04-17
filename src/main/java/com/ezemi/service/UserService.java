package com.ezemi.service;

import java.util.List;

import com.ezemi.entity.Address;
import com.ezemi.entity.BankDetails;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.Order;
import com.ezemi.entity.Transaction;
import com.ezemi.entity.User;

public interface UserService {
	
	
	public List<Transaction> getTransactionsByUserId(int userId);
	
	public EmiCard getEmiCard(int userId);
	
	public void updateAddress(int userId, Address address);
	
	public void updateBankDetails(int userId,BankDetails bankDetails);
	
	public void payEmi(int userId, int orderId);

}
