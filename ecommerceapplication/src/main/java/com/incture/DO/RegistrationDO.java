package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="REGISTRATION_DETAILS")
public class RegistrationDO {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name="FIRST_NAME",nullable=false,length=40)
	private String firstName;
	
	@Column(name="LAST_NAME",nullable=false,length=40)
	private String lastName;
	
	@Column(name="CONTACT_NUMBER",nullable=false,length=10)
	private String contactNumber;
	
	@Column(name="EMAIL",nullable=false)
	private String email;
	
	@Column(name="GENDER",nullable=false,length=1)
	private String gender;
	
	@Column(name="PASSWORD",nullable=false)
	private String password;
	
	@Column(name="DATE_OF_BIRTH")
	private String dateOfBirth;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return "RegistrationDO [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", contactNumber=" + contactNumber + ", email=" + email + ", gender=" + gender + ", password="
				+ password + ", dateOfBirth=" + dateOfBirth + "]";
	}

	
}
