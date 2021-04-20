package com.ezemi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.Address;
import com.ezemi.entity.BankDetails;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.Transaction;
import com.ezemi.entity.User;

import com.ezemi.repository.UserRepository;
import com.ezemi.repository.TransactionRepository;
import com.ezemi.repository.UserRepository;
import com.ezemi.service.EmailService;

import com.ezemi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;


	@Autowired
	TransactionRepository transactionRepo;
	
	@Autowired
	EmailService emailService;
	

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

		userRepo.activateCard(userId);
		User user=userRepo.getUserById(userId);
		EmiCard em=user.getCard();
		double trfAmount;
		if(em.getCardType().getCardTypeId()==1) {
			trfAmount=300.0;
		}
		else {
			 trfAmount=500.0;
		}
		String message="Paid for Card Activation";
		transactionRepo.addTransaction(userId, trfAmount, message);
		
		String subject = "Transaction Successful";
		String text = user.getFirstname() + " your card has been activated.";
		emailService.sendEmail(user.getEmail(), text, subject);
	}



	@Override
	public User getUserById(int userId) {
		User user=userRepo.getUserById(userId);
		return user;
	}
	
}
