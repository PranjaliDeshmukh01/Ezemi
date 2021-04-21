package com.ezemi.repositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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



	@Override
	public Transaction getTransactionById(UUID transactionId) {
	 String jpql="select t from Transaction t where t.transactionId=:tId";
	TypedQuery<Transaction> query=em.createQuery(jpql, Transaction.class);
	query.setParameter("tId", transactionId);
	
	return query.getSingleResult();
		
	}
	
}
