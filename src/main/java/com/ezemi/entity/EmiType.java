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
@Table(name="tbl_EmiType")
public class EmiType {


	@Id
	@SequenceGenerator(name = "emiType_seq", initialValue = 11, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emiType_seq")
	int emiTypeId;
	
	int numberOfMonths;
	
	@OneToMany(mappedBy="emiType")
	List<Order> orders;

	public int getEmiTypeId() {
		return emiTypeId;
	}

	public void setEmiTypeId(int emiTypeId) {
		this.emiTypeId = emiTypeId;
	}

	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}
