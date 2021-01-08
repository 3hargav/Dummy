package com.incture.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.GuestLoginDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.GuestLoginService;

@RestController
@RequestMapping(value="/guestLogin")
public class GuestLoginController {
	
	@Autowired
	GuestLoginService guestLoginService;
	
	@PostMapping(value="/generateOtp")
	public ResponseMessage getEmailOtp(@RequestBody GuestLoginDTO guestLoginDTO) throws IOException {

		return guestLoginService.addGuestOtpDetails(guestLoginDTO);
	}
	
	@PostMapping(value="/validateOtp")
	public ResponseMessage processotp(@RequestBody GuestLoginDTO guestLoginDTO) throws IOException {
		return guestLoginService.validateOtp(guestLoginDTO);
	}

}
