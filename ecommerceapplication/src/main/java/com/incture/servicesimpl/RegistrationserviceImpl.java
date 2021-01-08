package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.RegistrationDAO;
import com.incture.DO.RegistrationDO;
import com.incture.DTO.RegistrationDTO;
import com.incture.DTO.loginDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.Registrationservice;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class RegistrationserviceImpl implements Registrationservice {
	@Autowired
	RegistrationDAO registrationDAO;

	public ResponseMessage addDetails(RegistrationDTO registrationDTO) {

		ResponseMessage responseMessage=new ResponseMessage();
		try{
			
			if(!ServicesUtil.isEmpty(registrationDTO)){
				registrationDAO.addDetails(registrationDTO);
				responseMessage.setStatusMessage("Successfully created account");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in RegistrationserviceImpl.addDetails():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in RegistrationserviceImpl.addDetails():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	@Override
	public ResponseMessage checkLogin(loginDTO loginDto) {
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(ServicesUtil.isEmpty(loginDto.getUserName()) || ServicesUtil.isEmpty(loginDto.getPassword()) ){
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				String status=registrationDAO.checkLogin(loginDto);
				System.err.println("status:"+status);
				if(status.equals("Login Success")){
					RegistrationDO userDetails = registrationDAO.getUserProfile(loginDto.getUserName());
					RegistrationDTO userProfileDetails = registrationDAO.ExportFromDB(userDetails);
//					
					responseMessage.setStatusMessage("Login success");
					responseMessage.setStatusCode(200);
					responseMessage.setObject(userProfileDetails);					
				}
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
	

	public ResponseMessage fetchdata(){
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> registrationDTOs=null;
		try{
			List<RegistrationDO> registrations=registrationDAO.getAllData();
			if(!ServicesUtil.isEmpty(registrations)){
				registrationDTOs=new ArrayList<Object>();
				for(RegistrationDO registration:registrations){
					registrationDTOs.add(registrationDAO.ExportFromDB(registration));
				}
			}responseMessage.setStatusMessage("fetch success");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(registrationDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("fetching failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(registrationDTOs);
			return responseMessage;
		}

	}
}