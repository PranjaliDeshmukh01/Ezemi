package com.ezemi.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Repository;

import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void addOrUpdareAProduct(Product product) {
		em.merge(product);
	}

	@Override
	public Product getProductById(int productId) {
		return em.find(Product.class, productId);
	}

	@Override
	public List<Product> getAllProducts() {
		String jpql = "select p from Product p";
		TypedQuery<Product> query = em.createQuery(jpql, Product.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteAProduct(int productId) {
		String jpql = "delete from Product p where p.productId=:pId";
		Query query = em.createQuery(jpql);
		query.setParameter("pId", productId);

		query.executeUpdate();

	}

	@Override
	public boolean inStockToggle(int productId) {
		Product product = em.find(Product.class, productId);
		boolean state = !product.isInStock();
		product.setInStock(state);
		return state;
	}
	
	@Override
	public List<Category> getAllCategories(){
		String jpql ="select c from Category c";
		return em.createQuery(jpql, Category.class).getResultList();
	}
}