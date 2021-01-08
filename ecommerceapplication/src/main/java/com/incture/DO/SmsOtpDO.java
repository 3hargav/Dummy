package com.incture.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SMS_OTP_DETAILS")
public class SmsOtpDO {
    
	@Id
	@Column(name="SMS_ID")
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	private int id;
	
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	
	@Column(name="SMS_OTP")
	private String otp;
	
	@Column(name="VERIFIED")
	private Boolean verified;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	@Override
	public String toString() {
		return "SmsOtpDO [id=" + id + ", mobileNumber=" + mobileNumber + ", otp=" + otp + ", verified=" + verified
				+ "]";
	}
	
	
	

}
