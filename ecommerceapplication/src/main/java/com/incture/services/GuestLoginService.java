package com.incture.services;

import com.incture.DTO.GuestLoginDTO;
import com.incture.response.ResponseMessage;

public interface GuestLoginService {

	ResponseMessage addGuestOtpDetails(GuestLoginDTO guestLoginDTO);

	ResponseMessage validateOtp(GuestLoginDTO guestLoginDTO);

}
