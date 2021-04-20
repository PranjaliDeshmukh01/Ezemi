package com.ezemi.service;

import java.util.List;

import com.ezemi.entity.Category;
import com.ezemi.entity.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	Product getProductById(int userId);
	
	List<Category> getAllCategory();
	
	boolean inStockToggle(int productId);
	
	
}
