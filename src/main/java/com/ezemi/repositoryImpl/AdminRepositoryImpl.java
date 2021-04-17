package com.ezemi.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ezemi.entity.Bank;
import com.ezemi.entity.CardType;
import com.ezemi.entity.Category;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.EmiType;
import com.ezemi.entity.Order;
import com.ezemi.entity.User;
import com.ezemi.repository.AdminRepository;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
	
	@PersistenceContext 
	EntityManager em;
	

	@Override
	@Transactional
	public void addOrUpdateBank(Bank bank) {
		
		em.merge(bank);
		
	}

	@Override
	public Bank getBankById(int bankId) {
		return em.find(Bank.class, bankId);
	}
	
	@Override
	public List<User> getUsersByBankId(int bankId) {
		String jpql = "select u from User u where u.bankDetaills.bank.bankId=:bId";
		TypedQuery<User> query = em.createQuery(jpql,User.class);
		query.setParameter("bId", bankId);
		return query.getResultList();
	}
	
	@Override
	@Transactional
	public void addOrUpdatecategory(Category ct) {

		em.merge(ct);

	}
	
	@Override
	@Transactional
	public void addOrUpdateCardType(CardType ct) {

		em.merge(ct);

	}
	
	@Override
	public CardType getCardTypeById(int ctId){
		return em.find(CardType.class, ctId);
	}
	
	@Override
	public Category getCategoryById(int categoryId){
		return em.find(Category.class, categoryId);
	}
	
	@Override
	@Transactional
	public void addEmiType(int numberOfMonths){
		EmiType et = new EmiType();
		et.setNumberOfMonths(numberOfMonths);
	
		em.merge(et);
	
	}
	
	@Override
	public EmiType getEmiTypeById(int emiTypeId){
		return em.find(EmiType.class, emiTypeId);
	}
	
	@Override
	@Transactional
	public void changeCardofUser(String emiCardNo, int cardTypeId){
		EmiCard card = em.find(EmiCard.class, emiCardNo);
		CardType ct = em.find(CardType.class, cardTypeId);
		card.setCardType(ct);
	
		em.merge(card);
	
	}
	
	@Override
	public void autoDebit(){
		String jpql = "select o from Order o where o.autoDebit=:tr and ";
		TypedQuery<Order> query = em.createQuery(jpql, Order.class);
		query.setParameter("tr", true);
	}
}
