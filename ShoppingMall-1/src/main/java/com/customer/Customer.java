package com.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customerdetails")

public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int orderId;
	private String phone;
	private String email;

	public Integer getId() {
		return id;
	}

	public void setid(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Customer () {
		super();
	}
	
	
	 public Customer(int id, String name, int orderId, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.orderId = orderId;
		this.phone = phone;
		this.email = email;
	}

	 @Override
	    public String toString() {
	        return "Customer [CustomerId =" + id + ", name =" + name + ", Orderid = "+ orderId +" email =" + email + ", phoneNumber =" + phone + "]";
	    }

}
