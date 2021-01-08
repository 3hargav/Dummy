package com.incture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.DTO.AdminLoginDTO;
import com.incture.DTO.ChangeAdminLoginDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.AdminLoginService;

@RestController
@RequestMapping(value="/adminlog")
public class AdminLoginController {

	@Autowired
	AdminLoginService adminLoginService;
	
	@PostMapping(value="/login")
	public ResponseMessage AdminLogin(@RequestBody AdminLoginDTO adminLoginDTO){
		return adminLoginService.checkAdminLogin(adminLoginDTO);
	}
	
	@PostMapping(value="/adminregister")
	public ResponseMessage AdminRegister(@RequestBody AdminLoginDTO adminLoginDTO){
		return adminLoginService.addAdminDetails(adminLoginDTO);
	}
	
	@PostMapping(value="/updateEmail")
	public ResponseMessage UpdateEmail(@RequestBody AdminLoginDTO adminLoginDTO, @RequestBody ChangeAdminLoginDTO changeAdminLoginDTO){
		return adminLoginService.modify_admin_email(adminLoginDTO, changeAdminLoginDTO);
	}
	
	@GetMapping(value="/AdminLoginDetails")
	public ResponseMessage fetchdata(){
		return adminLoginService.fetchdata();
	}
}
