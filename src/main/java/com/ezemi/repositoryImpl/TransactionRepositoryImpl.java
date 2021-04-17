package com.ezemi.repositoryImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ezemi.entity.Transaction;
import com.ezemi.entity.User;
import com.ezemi.repository.TransactionRepository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Transaction> getTransactionByUserId(int userId) {
		User user = em.find(User.class, userId);
		return user.getTransactions();
	}



	@Override
	@Transactional
	public void addTransaction(int userId, double trfAmount, String msg) {
		Transaction transaction = new Transaction();
		transaction.setAmount(trfAmount);
		transaction.setDate(LocalDate.now());
		transaction.setTransactionMsg(msg);
		transaction.setUser(em.find(User.class, userId));
		em.merge(transaction);
	}
	
}
