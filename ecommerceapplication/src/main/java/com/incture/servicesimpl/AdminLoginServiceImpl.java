package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.AdminLoginDAO;
import com.incture.DO.AdminLoginDO;
import com.incture.DTO.AdminLoginDTO;
import com.incture.DTO.ChangeAdminLoginDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.AdminLoginService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class AdminLoginServiceImpl implements AdminLoginService {

	@Autowired
	AdminLoginDAO adminLoginDAO;
	
	public ResponseMessage checkAdminLogin(AdminLoginDTO adminLoginDTO){
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(ServicesUtil.isEmpty(adminLoginDTO.getEmail()) || ServicesUtil.isEmpty(adminLoginDTO.getPassword()) ){
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				String status=adminLoginDAO.checkAdminLogin(adminLoginDTO);
				System.err.println("status:"+status);
				responseMessage.setStatusMessage(status);
				responseMessage.setStatusCode(200);
				return responseMessage;
			}
			
		}catch(Exception e){
			System.err.println("Exception in RegistrationserviceImpl.checkLogin():-"+e.getMessage());
			responseMessage.setStatusMessage("Exception in RegistrationserviceImpl.checkLogin():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}
	
		public ResponseMessage addAdminDetails(AdminLoginDTO adminLoginDTO) {

			ResponseMessage responseMessage=new ResponseMessage();
			try{
				
				if(!ServicesUtil.isEmpty(adminLoginDTO)){
					adminLoginDAO.addAdminDetails(adminLoginDTO);
					responseMessage.setStatusMessage("Successfully created Admin account");
					responseMessage.setStatusCode(200);
					return responseMessage;
				}else{
					responseMessage.setStatusMessage("Received Null Object");
					responseMessage.setStatusCode(200);
					return responseMessage;
				}

			}catch(Exception e){
				System.err.println("Exception in AdminLoginServiceImpl.addAdminDetails():-"+e.getMessage());
				responseMessage.setStatusMessage("Sorry!! Failed:Exception in AdminLoginServiceImpl.addAdminDetails():-"+e.getMessage());
				responseMessage.setStatusCode(500);
				return responseMessage;
			}

	}

		
		public ResponseMessage modify_admin_email(AdminLoginDTO adminLoginDTO,ChangeAdminLoginDTO changeAdminLoginDTO) {

			ResponseMessage responseMessage=new ResponseMessage();
			try{
					if(!ServicesUtil.isEmpty(adminLoginDTO.getEmail())){
					adminLoginDAO.modify_admin_email(adminLoginDTO,changeAdminLoginDTO);
					responseMessage.setStatusMessage("Successfully changed account email");
					responseMessage.setStatusCode(200);
					return responseMessage;
				}else{
					responseMessage.setStatusMessage("Received Null Object");
					responseMessage.setStatusCode(200);
					return responseMessage;
				}

			}catch(Exception e){
				System.err.println("Exception in AdminLoginServiceImpl.addAdminDetails():-"+e.getMessage());
				responseMessage.setStatusMessage("Sorry!! Failed:Exception in AdminLoginServiceImpl.addAdminDetails():-"+e.getMessage());
				responseMessage.setStatusCode(500);
				return responseMessage;
			}

	}
		
		public ResponseMessage fetchdata(){
			ResponseMessage responseMessage=new ResponseMessage();
			List<Object> AdminLoginDTOs=null;
			try{
				List<AdminLoginDO> adminlogins=adminLoginDAO.getAllData();
				if(!ServicesUtil.isEmpty(adminlogins)){
					AdminLoginDTOs=new ArrayList<Object>();
					for(AdminLoginDO adminLogin:adminlogins){
						AdminLoginDTOs.add(adminLoginDAO.ExportFromDB(adminLogin));
					}
				}responseMessage.setStatusMessage("fetch success");
				responseMessage.setStatusCode(200);
				responseMessage.setObjectList(AdminLoginDTOs);
				return responseMessage;
			}catch(Exception e){
				responseMessage.setStatusMessage("fetching failed");
				responseMessage.setStatusCode(500);
				responseMessage.setObjectList(AdminLoginDTOs);
				return responseMessage;
			}

		}

		
}
