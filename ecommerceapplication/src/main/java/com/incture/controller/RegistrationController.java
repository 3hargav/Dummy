package com.incture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.RegistrationDTO;
import com.incture.DTO.loginDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.Registrationservice;

@RestController
@RequestMapping(value="/service")
public class RegistrationController {

	@Autowired
	Registrationservice registrationservice;

	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcomemsg(){
		return "Hello";
	}

	//@RequestMapping(value="/register", method=RequestMethod.POST)
	@PostMapping("/register")
	public ResponseMessage reg_service_controller(@RequestBody RegistrationDTO registrationDTO){
		return registrationservice.addDetails(registrationDTO);	
	}

	@PostMapping("/login")
	public ResponseMessage processForm(@RequestBody loginDTO loginDto) {

		return  registrationservice.checkLogin(loginDto);

	}
	
	@GetMapping("/fetchdata")
	public ResponseMessage getdata(){
		return registrationservice.fetchdata();
	}
}
