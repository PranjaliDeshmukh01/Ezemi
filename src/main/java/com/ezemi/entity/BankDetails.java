package com.ezemi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_BankDetails")
public class BankDetails {
	
	@Id
	@SequenceGenerator(name = "bankDetails_seq", initialValue = 20001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankDetails_seq")
	int bankDetailsId;
	
	String account_number;
	
	String IFSC_code;
	
	@ManyToOne
	Bank bank;
	
	@OneToOne
	User user;

	public int getBankDetailsId() {
		return bankDetailsId;
	}

	public void setBankDetailsId(int bankDetailsId) {
		this.bankDetailsId = bankDetailsId;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getIFSC_code() {
		return IFSC_code;
	}

	public void setIFSC_code(String iFSC_code) {
		IFSC_code = iFSC_code;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
