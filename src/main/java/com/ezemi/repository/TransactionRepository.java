package com.ezemi.repository;

import java.util.List;

import com.ezemi.entity.Transaction;

public interface TransactionRepository {

	public List<Transaction> getTransactionByUserId(int userId);
	
	public void addTransaction(int userId, double trfAmount, String msg);
	
	public void reduceCreditBy(int userId, double amount);
}
