package com.incture.services;

import com.incture.DTO.RegistrationDTO;
import com.incture.DTO.loginDTO;
import com.incture.response.ResponseMessage;

public interface Registrationservice {

	ResponseMessage addDetails(RegistrationDTO registrationDTO);

	ResponseMessage checkLogin(loginDTO loginDto);
	
	 ResponseMessage fetchdata();
	
}
