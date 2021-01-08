package com.incture.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.SmsOtpDTO;
import com.incture.response.ResponseMessage;
//import com.incture.DTO.OtpDTO1;
import com.incture.services.Otpservice;

@RestController
@RequestMapping(value="/sms")
public class OtpController {
     
	@Autowired
	Otpservice otpservice;    
	
		@PostMapping(value="/generateOtp")
	 public ResponseMessage getOtp(@RequestBody SmsOtpDTO otpdto) throws IOException {
			System.out.println("Otp Service started");
			 return otpservice.addOtpDetails(otpdto);
		 }
		@PostMapping(value="/validateOtp")
		public ResponseMessage otpvalidate(@RequestBody SmsOtpDTO otpdto) throws IOException{
			return otpservice.checkotp(otpdto);
		}

		
		@GetMapping("/fetchdata")
	    public ResponseMessage getdata(){
	        return otpservice.fetchotpdata();
	    }
		
}
