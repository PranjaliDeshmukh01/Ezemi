package com.ezemi.repository;

import java.util.List;

import com.ezemi.entity.Bank;
import com.ezemi.entity.User;

public interface BankRepository {
	
	public void addOrUpdateBank(Bank bank);
	
	public Bank getBankById(int bankId);
	
	public List<User> getUsersByBankId(int bankId);

	List<Bank> getAllBanks();
}
