package com.incture.DTO;

public class SmsOtpDTO {
	
	private String mobile_number;
	private String otp;
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	@Override
	public String toString() {
		return "OtpDTO [mobile_number=" + mobile_number + ", otp=" + otp + "]";
	}
	
	
}
