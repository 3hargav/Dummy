package com.incture.servicesimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.GuestLoginDAO;
import com.incture.DTO.GuestLoginDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.GuestLoginService;
import com.incture.util.HttpStatusCodes;
import com.incture.util.ServicesUtil;
import com.incture.util.SmsClass;

@Service
@Transactional
public class GuestLoginServiceImpl implements GuestLoginService{
	
	@Autowired
	GuestLoginDAO guestLoginDAO;
	
	@Autowired
	SmsClass smsClass;


	public ResponseMessage addGuestOtpDetails(GuestLoginDTO guestLoginDTO) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{	
			if(!ServicesUtil.isEmpty(guestLoginDTO.getMobileNumber())){
				String otp=ServicesUtil.GenerateOtp(5);
				guestLoginDTO.setOtp(ServicesUtil.encodeString(otp));
				guestLoginDAO.addOtpDetails(guestLoginDTO);
				smsClass.sms("otp is:"+otp,guestLoginDTO.getMobileNumber());
				responseMessage.setStatusMessage("Successfully sent otp");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}
			}catch(Exception e){
				System.err.println("Exception in addGuestotpdetails-"+e.getMessage());
				responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorCode+":Sorry!! Failed:Exception in addGuestotpdetails-"+e.getMessage());
				responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
				return responseMessage;
			}
	}
		

	@Override
	public ResponseMessage validateOtp(GuestLoginDTO guestLoginDTO) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		
		try{
			if(ServicesUtil.isEmpty(ServicesUtil.isEmpty(guestLoginDTO.getOtp()))){
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}else{
				String status=guestLoginDAO.checkotp(guestLoginDTO);
				System.err.println("status:"+status);
				responseMessage.setStatusMessage(status);
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}
			
		}catch(Exception e){
			System.err.println("Exception in GuestLoginserviceserviceImpl.checkotp():-"+e.getMessage());
			responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorCode+":Exception in GuestLginserviceImpl.checkotp():-"+e.getMessage());
			responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
			return responseMessage;
		}
		
		}
	}
		
