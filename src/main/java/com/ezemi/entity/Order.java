package com.ezemi.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_Order")
public class Order {

	@Id
	@SequenceGenerator(name = "order_seq", initialValue = 80001, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
	int orderId;
	
	@ManyToOne
	User user;
	
	@ManyToOne
	Product product;
	
	LocalDate orderDate;
	
	int emiMonths;
	
	int installments;
	
	double emiAmount;
	
	double amountDue;
	
	double orderCost;

	Boolean autoDebit;
	
	boolean isShipped;
	
	
	
	public int getEmiMonths() {
		return emiMonths;
	}

	public void setEmiMonths(int emiMonths) {
		this.emiMonths = emiMonths;
	}

	public boolean isShipped() {
		return isShipped;
	}

	public void setShipped(boolean isShipped) {
		this.isShipped = isShipped;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}
	
	public double getOrderCost() {
		return orderCost;
	}

	public void setOrderCost(double orderCost) {
		this.orderCost = orderCost;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getInstallments() {
		return installments;
	}

	public void setInstallments(int installments) {
		this.installments = installments;
	}

	public double getEmiAmount() {
		return emiAmount;
	}

	public void setEmiAmount(double emiAmount) {
		this.emiAmount = emiAmount;
	}

	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public LocalDate getDate() {
		return orderDate;
	}

	public void setDate(LocalDate date) {
		this.orderDate = date;
	}

	public Boolean getAutoDebit() {
		return autoDebit;
	}

	public void setAutoDebit(Boolean autoDebit) {
		this.autoDebit = autoDebit;
	}
	
	
	
}
