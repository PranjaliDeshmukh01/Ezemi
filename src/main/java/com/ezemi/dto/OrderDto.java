package com.ezemi.dto;

public class OrderDto {
	
	int userId;
	
	int productId;
	
	int emimonths;
	
	boolean autoDebit;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getEmimonths() {
		return emimonths;
	}

	public void setEmimonths(int emimonths) {
		this.emimonths = emimonths;
	}

	public boolean isAutoDebit() {
		return autoDebit;
	}

	public void setAutoDebit(boolean autoDebit) {
		this.autoDebit = autoDebit;
	}
	
	

}
