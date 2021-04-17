package com.ezemi.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {

	String productName;

	Double price;

	String productDetails;	

	double processingFee;

	MultipartFile productImgUrl;
	
	int categoryId;


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(double processingFee) {
		this.processingFee = processingFee;
	}

	public MultipartFile getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(MultipartFile productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
	
	

}
