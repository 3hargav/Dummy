package com.incture.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ecommerce")
public class HomeController {

	@RequestMapping(value="/msg",method = RequestMethod.GET)
	public String sayHello(){
		System.err.println("greeting Hello sleepyHead");
		return "greeting Hello sleepyHead";
	}
}
