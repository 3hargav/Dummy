package com.incture.servicesimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.ForgotPasswordDAO;
import com.incture.DAO.RegistrationDAO;
import com.incture.DTO.ForgotPasswordDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.ForgotPasswordService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService{

	@Autowired
	ForgotPasswordDAO forgotPasswordDAO;
	
	@Autowired
	RegistrationDAO registrationDAO;
	
	public ResponseMessage ValidatePassword(ForgotPasswordDTO forgotPasswordDTO){
		ResponseMessage responseMessage=new ResponseMessage();
		try{
		if(ServicesUtil.isEmpty(forgotPasswordDTO.getPassword())||ServicesUtil.isEmpty(forgotPasswordDTO.getConfirm_password())){
		responseMessage.setStatusMessage("Didn't receive both passwords(null object)");
		responseMessage.setStatusCode(200);
		return responseMessage;
		}
		if(!ServicesUtil.isEmpty(forgotPasswordDTO.getPassword())&&!ServicesUtil.isEmpty(forgotPasswordDTO.getConfirm_password())&&(forgotPasswordDTO.getPassword().equals(forgotPasswordDTO.getConfirm_password()))){
			forgotPasswordDAO.SetNewPassword(forgotPasswordDTO);
			responseMessage.setStatusMessage("Password changed");
			responseMessage.setStatusCode(200);
			return responseMessage;
		}
		else{
			responseMessage.setStatusMessage("Both passwords don't match.Cannot change password");
			responseMessage.setStatusCode(200);
			return responseMessage;
		}
		}catch(Exception e){
			//System.out.println("Exception in ForgotPasswordServiceImpl.ValidatePassword()"+e.getMessage());
			responseMessage.setStatusMessage("Exception in ForgotPasswordServiceImpl.ValidatePassword()"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
		
}

	public ResponseMessage checkEmail(ForgotPasswordDTO forgotPasswordDTO) {
		ResponseMessage responseMessage=new ResponseMessage();
		try{
	        if(!ServicesUtil.isEmpty(forgotPasswordDTO.getEmail_id())){
	        String status = registrationDAO.checkUser(forgotPasswordDTO.getEmail_id());
			responseMessage.setStatusMessage(status);
			responseMessage.setStatusCode(200);
			return responseMessage;
			}
	        else{
	        	responseMessage.setStatusMessage("Received null email");
				responseMessage.setStatusCode(200);
				return responseMessage;
	        }
		}catch(Exception e){
			responseMessage.setStatusMessage("Checking email failed");
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
			
	}

}