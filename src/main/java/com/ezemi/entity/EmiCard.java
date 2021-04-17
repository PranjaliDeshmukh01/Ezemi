package com.ezemi.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ezemi.helper.RandomPinGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tbl_EmiCard")
public class EmiCard {

	@Id
	String emiCardNo;
	
	double credit;
	
	double creditLeft;
	
	@ManyToOne
	CardType cardType;
	
	LocalDate expiryDate;
	
	Boolean isActivated;
	
	public EmiCard(){
		this.emiCardNo = RandomPinGenerator.generate16digitNumber();
	}
	
	@OneToOne
	User user;

	public String getEmiCardId() {
		return emiCardNo;
	}

	public void setEmiCardId(String emiCardNo) {
		this.emiCardNo = emiCardNo;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getCreditLeft() {
		return creditLeft;
	}

	public void setCreditLeft(double creditLeft) {
		this.creditLeft = creditLeft;
	}
	
	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmiCardNo() {
		return emiCardNo;
	}

	public void setEmiCardNo(String emiCardNo) {
		this.emiCardNo = emiCardNo;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	
	
}
