package com.ezemi.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_Bank")
public class Bank {
	
	@Id
	@SequenceGenerator(name = "bank_seq", initialValue = 51, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_seq")
	int bankId;
	
	String bankName;
	
	@OneToMany(mappedBy="bank")
	List<BankDetails> userBankDetails;

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@JsonIgnore
	public List<BankDetails> getUserBankDetails() {
		return userBankDetails;
	}

	public void setUserBankDetails(List<BankDetails> userBankDetails) {
		this.userBankDetails = userBankDetails;
	}
	
	
}
