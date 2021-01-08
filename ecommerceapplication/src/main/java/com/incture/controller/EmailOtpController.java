package com.incture.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.EmailOtpDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.EmailOtpService;

@RestController
@RequestMapping(value="/email")
public class EmailOtpController {

	@Autowired
	EmailOtpService emailOtpService;

	@PostMapping(value="/generateOtp")
	public ResponseMessage getEmailOtp(@RequestBody EmailOtpDTO emailOtpDTO) throws IOException {
		System.out.println("Email Otp service initiated");
		return emailOtpService.addEmailOtpDetailsNew(emailOtpDTO);
	}
	
	@PostMapping(value="/validateOtpr")
	public ResponseMessage processotp(@RequestBody EmailOtpDTO emailOtpDTO) throws IOException {
		return emailOtpService.validateEmailOtp(emailOtpDTO);
	}

	@GetMapping("/fetchdata")
	public ResponseMessage getEmaildata(){
		return emailOtpService.fetchdata();
	}
}
