package com.ezemi.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezemi.entity.Address;
import com.ezemi.entity.BankDetails;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.Transaction;
import com.ezemi.entity.User;
import com.ezemi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public List<Transaction> getTransactionsByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public EmiCard getEmiCard(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public void updateAddress(int userId, Address address) {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void updateBankDetails(int userId, BankDetails bankDetails) {
		// TODO Auto-generated method stub

	}

	
	
	@Override
	public void payEmi(int userId, int orderId) {
		// TODO Auto-generated method stub

	}



	@Override
	public void activateCard(int userId) {
		
		
	}
}
