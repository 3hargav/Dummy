package com.incture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.ForgotPasswordDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.ForgotPasswordService;

@RestController
@RequestMapping(value="/forgotpassword")
public class ForgotPasswordController {

	@Autowired
	ForgotPasswordService forgotPasswordService;
	
	@PostMapping(value = "/check")
	public ResponseMessage CheckingRecord(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
		return forgotPasswordService.checkEmail(forgotPasswordDTO);
	}
	
	@PutMapping(value="/changepassword")
	public ResponseMessage ForgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO){
		return forgotPasswordService.ValidatePassword(forgotPasswordDTO);
	}
	
}
