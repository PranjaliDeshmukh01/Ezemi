package com.ezemi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_CardType")
public class CardType {
	
	@Id
	@SequenceGenerator(name = "cardType_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardType_seq")
	int cardTypeId;
	
	String cardTypeName;
	
	Double creditAmount;

	public int getCardTypeId() {
		return cardTypeId;
	}

	public void setCardTypeId(int cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public Double getCreditAmount() {
		return creditAmount;
	}


	public void setCreditAmount(Double creditAmount) {

		this.creditAmount = creditAmount;
	}



	
}
