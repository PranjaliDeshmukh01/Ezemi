package com.ezemi.repositoryImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ezemi.dto.RoleType;
import com.ezemi.entity.Address;
import com.ezemi.entity.Bank;
import com.ezemi.entity.BankDetails;
import com.ezemi.entity.EmiCard;
import com.ezemi.entity.User;
import com.ezemi.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void registerorUpdateUser(User user) {
		em.merge(user);
	}

	@Override
	public User getUserByEmail(String email) {
		String jpql = "select u from User u where u.userEmail=:ue";
		return em.createQuery(jpql, User.class)
				.setParameter("ue", email)
				.getSingleResult();
	}

	@Override
	public User isValid(String userEmail, String userPassword) {
		User u = getUserByEmail(userEmail);
		if (u != null && u.getPassword().equals(userPassword)) {
			return u;
		}
		return null;
	}

	@Override
	public User getUserById(int userId) {
		return em.find(User.class, userId);
	}

	@Override
	public void changePassword(int userId, String password) {
		User user = getUserById(userId);
		user.setPassword(password);
		registerorUpdateUser(user);
	}

	@Override
	@Transactional
	public void activateCard(String cardNo) {
		EmiCard card = em.find(EmiCard.class, cardNo);
		card.setIsActivated(true);
		card.setExpiryDate(LocalDate.now().plusYears(2));

		em.merge(card);

	}

	@Override
	public void approveUser(int userId) {
		User user = getUserById(userId);
		user.setIsApproved(true);
		registerorUpdateUser(user);
	}

	@Override
	public void deleteUserById(int userId) {
		em.remove(em.find(User.class, userId));
	}

	@Override
	public List<User> getAllCustomers() {
		String jpql = "select u from User u where User.role=:r";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("r", RoleType.Customer);
		return query.getResultList();
	}
	
	@Override
	public List<User> getApprovedCustomers() {
		String jpql = "select u from User u where User.role=:r and User.isApproved=:tr";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("r", RoleType.Customer);
		query.setParameter("tr", true);
		return query.getResultList();
	}
	
	@Override
	public List<User> getNotApprovedCustomers() {
		String jpql = "select u from User u where User.role=:r  and User.isApproved=:f";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("r", RoleType.Customer);
		query.setParameter("f", false);
		return query.getResultList();
	}
	
	
	
	@Override
	public List<User> getAllUsersByCardTypeId(int cardTypeid) {
		String jpql = "select u from User u where u.card.cardType.cardTypeId=:ctId";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("ctId", cardTypeid);
		return query.getResultList();
	}

	@Override
	public List<User> getUsersByName(String uname) {
		String jpql = "select u from User u where u.firstname=:nm";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("nm", uname);
		return query.getResultList();
	}

	
	
	@Override
	public EmiCard getCardByUserId(int userId) {
		return getUserById(userId).getCard();
	}

	@Override
	@Transactional
	public void addOrUpdateAddress(Address address, int userId) {
		User user = getUserById(userId);
		address.setUser(user);
		em.merge(address);
	}

	@Override
	@Transactional
	public void addOrUpdateBankdetails(BankDetails details, int userId) {
		User user = getUserById(userId);
		details.setUser(user);
		em.merge(details);
	}
	
	@Override
	public Boolean userExists(String email) {
		User u = getUserByEmail(email);
		return u!=null;
	}
}
