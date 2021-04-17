package com.ezemi.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezemi.entity.Product;
import com.ezemi.repository.ProductRepository;
import com.ezemi.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepo;

	@Override
	public List<Product> getAllProducts() {
		return productRepo.getAllProducts();
	}

	@Override
	public Product getProductById(int userId) {
		return productRepo.getProductById(userId);
	}

}
