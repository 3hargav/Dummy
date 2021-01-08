package com.incture.DTO;

import java.util.List;

public class SuperDTO {
	
	private Integer userId;
	private String firstname;
	private String lastname;
	private String contact;
	private String gender;
	private String dob;
	private String email;
	private String password;
	private Integer cartID;
	private Integer totalPice;
	private List<Object> objectList;
	private List<Object> objectList2;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCartID() {
		return cartID;
	}
	public void setCartID(Integer cartID) {
		this.cartID = cartID;
	}
	public Integer getTotalPice() {
		return totalPice;
	}
	public void setTotalPice(Integer totalPice) {
		this.totalPice = totalPice;
	}
	
	public List<Object> getObjectList() {
		return objectList;
	}
	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}
	
	public List<Object> getObjectList2() {
		return objectList2;
	}
	public void setObjectList2(List<Object> objectList2) {
		this.objectList2 = objectList2;
	}

}
