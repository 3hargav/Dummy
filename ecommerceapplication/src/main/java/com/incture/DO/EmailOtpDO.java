package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="EMAIL_OTP")
public class EmailOtpDO {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "EMAILOTP_ID")
	private Integer emailOtpId;
	
	@Column(name="OTP",nullable=false)
	private String otp;

	@Column(name="VERIFIED",nullable=false)
	private boolean varified;
	
	@Column(name="Email",nullable=false,unique=true)
	private String email;
	
	public Integer getEmailOtpId() {
		return emailOtpId;
	}


	public void setEmailOtpId(Integer emailOtpId) {
		this.emailOtpId = emailOtpId;
	}

	
	public String getOtp() {
		return otp;
	}


	public void setOtp(String otp) {
		this.otp = otp;
	}


	public boolean isVarified() {
		return varified;
	}


	public void setVarified(boolean varified) {
		this.varified = varified;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


}

