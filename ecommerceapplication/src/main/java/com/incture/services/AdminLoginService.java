package com.incture.services;

import com.incture.DTO.AdminLoginDTO;
import com.incture.DTO.ChangeAdminLoginDTO;
import com.incture.response.ResponseMessage;

public interface AdminLoginService {

	
	ResponseMessage modify_admin_email(AdminLoginDTO adminLoginDTO,ChangeAdminLoginDTO changeAdminLoginDTO);
	ResponseMessage checkAdminLogin(AdminLoginDTO adminLoginDTO);
	ResponseMessage addAdminDetails(AdminLoginDTO adminLoginDTO);
	ResponseMessage fetchdata();
}
