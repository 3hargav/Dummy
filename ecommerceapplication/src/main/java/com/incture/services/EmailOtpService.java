package com.incture.services;

import com.incture.DTO.EmailOtpDTO;
import com.incture.response.ResponseMessage;

public interface EmailOtpService {
	
	ResponseMessage addEmailOtpDetailsNew(EmailOtpDTO emailOtpDTO);
	
	ResponseMessage validateEmailOtp(EmailOtpDTO emailOtpDTO);

	ResponseMessage fetchdata();
	

}
