package com.incture.services;

import com.incture.DTO.SmsOtpDTO;
import com.incture.response.ResponseMessage;

public interface Otpservice {
	ResponseMessage checkotp(SmsOtpDTO otpDTO);
	ResponseMessage addOtpDetails(SmsOtpDTO otpdto);
	ResponseMessage fetchotpdata();	
}
