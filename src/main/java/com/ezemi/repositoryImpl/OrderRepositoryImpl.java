package com.ezemi.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ezemi.entity.Order;
import com.ezemi.entity.User;
import com.ezemi.repository.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void addAnOrder(Order order) {

		em.merge(order);

	}

	@Override
	@Transactional
	public void updateInstallment(int orderId) {
		Order order = em.find(Order.class, orderId);
		order.setAmountDue(order.getAmountDue() - order.getEmiAmount());
		order.setInstallments(order.getInstallments() + 1);
		em.merge(order);

	}

	@Override
	public List<Order> getOrdersByProductId(int product_id) {

		String jpql = "select o from Order o where o.product.productId:=pId";
		TypedQuery<Order> query = em.createQuery(jpql, Order.class);
		query.setParameter("pId", product_id);
		return query.getResultList();

	}

	@Override
	public void shipOrder(int orderId) {
		Order order = em.find(Order.class, orderId);
		order.setShipped(true);
		em.merge(order);
	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		User user = em.find(User.class, userId);
		return user.getOrders();
	}

}
