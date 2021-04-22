package com.ezemi.repository;

import java.util.List;
import java.util.UUID;

import com.ezemi.entity.Transaction;

public interface TransactionRepository {

	public List<Transaction> getTransactionByUserId(int userId);
	
	public void addTransaction(int userId, double trfAmount, String msg);
	
	public void reduceCreditBy(int userId, double amount);

	public Transaction getTransactionById(UUID transactionId);
	
	public void increaseCreditByUserId(int userId, double amount);
}
