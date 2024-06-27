package com.javaClass.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiffinorder")
public class Order {

	@Id
	@Column(name = "orderId")
	private int orderId;

	@Column(name = "userId")
	private int userId;

	@Column(name = "datePicked")
	private String datePicked;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDatePicked() {
		return datePicked;
	}

	public void setDatePicked(String datePicked) {
		this.datePicked = datePicked;
	}

}
