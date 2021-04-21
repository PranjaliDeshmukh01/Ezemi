package com.ezemi.dto;

public class LoginStatusDto {
	
	String email;
	
	int userId;
	
	String role;
	
	String name;
	
	String statusMsg;

	int cardType;
	
	double creditLeft;
	
	boolean isCardActivated;
	
	
	
	public boolean isCardActivated() {
		return isCardActivated;
	}

	public void setCardActivated(boolean isCardActivated) {
		this.isCardActivated = isCardActivated;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public double getCreditLeft() {
		return creditLeft;
	}

	public void setCreditLeft(double ceditLeft) {
		this.creditLeft = ceditLeft;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
}
