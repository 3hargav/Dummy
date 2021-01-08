package com.incture.services;

import com.incture.DTO.ForgotPasswordDTO;
import com.incture.response.ResponseMessage;

public interface ForgotPasswordService {
	ResponseMessage ValidatePassword(ForgotPasswordDTO forgotPasswordDTO);

	ResponseMessage checkEmail(ForgotPasswordDTO forgotPasswordDTO);
}
