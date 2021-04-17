	package com.ezemi.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ezemi.entity.Bank;
import com.ezemi.entity.User;
import com.ezemi.repository.BankRepository;

@Repository
public class BankRepositoryImpl implements BankRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public void addOrUpdateBank(Bank bank) {
		em.merge(bank);
	}

	@Override
	public void getBankById(int bankId) {
		em.find(Bank.class, bankId);
		
	}

	@Override
	public List<User> getUsersByBankId(int bankId) {
		String jpql = "select u from User u where u.bankDetails.bank.bankId=:bi";
		return em.createQuery(jpql,User.class)
				.setParameter("bi",bankId)
				.getResultList();
	}
	
	@Override
	public List<Bank> getAllBanks() {
		String jpql = "select b from Bank b";
		return em.createQuery(jpql,Bank.class).getResultList();
	}
	

}
