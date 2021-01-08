package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="ADMIN_LOGIN_DETAILS")
public class AdminLoginDO {

	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "ADMIN_ID")
	private Integer AdminId;

	@Column(name="ADMIN_EMAIL",nullable=false)
	private String email;

	@Column(name="PASSWORD",nullable= false)
	private String password;
	
	public Integer getAdminId() {
		return AdminId;
	}

	public void setAdminId(Integer adminId) {
		AdminId = adminId;
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


	
	
}
