package com.ezemi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_Category")
public class Category {

	@Id
	@SequenceGenerator(name = "catergory_seq", initialValue = 21, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catergory_seq")
	int categoryId;
	
	String categoryName;
	
	@OneToMany(mappedBy="category")
	List<Product> products;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
