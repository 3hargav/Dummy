package com.incture.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.QueryDTO;
import com.incture.DTO.RegistrationDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.UserPageService;

@RestController
@RequestMapping("/user")
public class UserPageController {

	@Autowired
	UserPageService userPageService;
	
	@GetMapping(value = "/fetchUserProfile/{userId}")
	public ResponseMessage getProductById(@PathVariable int userId) {
		return userPageService.GetUserProfile(userId);
	}
	
	@PutMapping(value = "/edit/{userId}")
	public ResponseMessage editAuser(@RequestBody RegistrationDTO registrationDTO, @PathVariable("userId") int userId){
		registrationDTO.setUserId(userId);
		return userPageService.editUser(registrationDTO, userId);
	}
	
	@PostMapping(value = "/query/{userId}")
	public ResponseMessage addQuery(@RequestBody QueryDTO queryDTO, @PathVariable("userId") int userId) {
		queryDTO.setUserid(userId);
		Date qdate = new Date();
		queryDTO.setQdate(qdate);
		queryDTO.setAnswer("Not Answered");
		
		return userPageService.addQuery(queryDTO, userId);
	}

	@GetMapping("/myQueries/{userId}")
	public ResponseMessage getQueriesUser(@PathVariable("userId") int userId){
		return userPageService.getMyQueries(userId);
	}
	
	@GetMapping("/admin/Queries")
	public ResponseMessage getQueries(){
		return userPageService.getAdminQueries();
	}
	
	@PutMapping(value = "admin/qupdate/{qid}")
	public ResponseMessage ansQuery(@RequestBody QueryDTO queryDTO, @PathVariable("qid") int qid){
		return userPageService.answerQuery(queryDTO, qid);
	}
}
