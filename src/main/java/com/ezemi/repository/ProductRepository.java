package com.ezemi.repository;

import java.util.List;

import com.ezemi.entity.Category;
import com.ezemi.entity.Product;

public interface ProductRepository {
	
	public void addOrUpdareAProduct(Product product);
	
	public Product getProductById(int productId);
	
	public List<Product> getAllProducts();
	
	public boolean inStockToggle(int productId);
	
	public void deleteAProduct(int productId);

	List<Category> getAllCategories();
}
