package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SHIPPING_ADDRESS")
public class ShippingAddressDO {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "ADDRESS_ID")
	private Integer addressID;
	
	@Column(name="ADDRESS",nullable=false,length=40)
	private String address;

	@Column(name="CITY",nullable=false,length=40)
	private String city;

	@Column(name="STATE",nullable=false,length=40)
	private String state;

	@Column(name="PINCODE",nullable=false,length=40)
	private Integer pincode;

	@Column(name="COUNTRY",nullable=false,length=40)
	private String country;
	
	@Column(name = "USER_ID")
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer addressID) {
		this.addressID = addressID;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}



}
