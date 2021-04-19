package com.ezemi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezemi.entity.Category;
import com.ezemi.entity.Product;
import com.ezemi.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
	

	@Autowired
	ProductService productService;
	
	@GetMapping(path="/allproducts")
	public List<Product> getAllProducts(){ 
		return productService.getAllProducts();
	}
	
	@GetMapping(path="/allcategory")
	public List<Category> getAllCategory(){
		return productService.getAllCategory();
	}
	
	
	
}
