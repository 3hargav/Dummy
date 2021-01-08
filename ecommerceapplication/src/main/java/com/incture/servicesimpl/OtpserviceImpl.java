package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.OtpDAO;
import com.incture.DO.SmsOtpDO;
import com.incture.DTO.SmsOtpDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.Otpservice;
import com.incture.util.HttpStatusCodes;
import com.incture.util.ServicesUtil;
import com.incture.util.SmsClass;


@Service
@Transactional
public class OtpserviceImpl implements Otpservice {
	@Autowired
	OtpDAO otpDAO;
	
	@Autowired
	SmsClass smsClass;
	public ResponseMessage addOtpDetails(SmsOtpDTO otpDTO){
		ResponseMessage responseMessage=new ResponseMessage();
	try{	
		if(!ServicesUtil.isEmpty(otpDTO.getMobile_number())){
			String otp=ServicesUtil.GenerateOtp(5);
			otpDTO.setOtp(ServicesUtil.encodeString(otp));
			otpDAO.addOtpDetails(otpDTO);
			smsClass.sms("otp is:"+otp,otpDTO.getMobile_number());
			responseMessage.setStatusMessage("Successfully sent otp");
			responseMessage.setStatusCode(HttpStatusCodes.successCode);
			return responseMessage;
		}else{
			responseMessage.setStatusMessage("Received Null Object");
			responseMessage.setStatusCode(HttpStatusCodes.successCode);
			return responseMessage;
		}

	}catch(Exception e){
		System.err.println("Exception in addotpdetails-"+e.getMessage());
		responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorCode+":Sorry!! Failed:Exception in addotpdetails-"+e.getMessage());
		responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
		return responseMessage;
	}
		
	}
	@Override
	public ResponseMessage checkotp(SmsOtpDTO otpDTO){
		ResponseMessage responseMessage=new ResponseMessage();
		
		try{
			if(ServicesUtil.isEmpty(ServicesUtil.isEmpty(otpDTO.getOtp()))){
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}else{
				String status=otpDAO.checkotp(otpDTO);
				System.err.println("status:"+status);
				responseMessage.setStatusMessage(status);
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}
			
		}catch(Exception e){
			System.err.println("Exception in OtpserviceserviceImpl.checkotp():-"+e.getMessage());
			responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorCode+":Exception in otpserviceImpl.checkotp():-"+e.getMessage());
			responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
			return responseMessage;
		}
		
		}
	@Override
	public ResponseMessage fetchotpdata() {
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> OtpDTOs=null;
		try{
			List<SmsOtpDO> Otps = otpDAO.getAllData();
			if(!ServicesUtil.isEmpty(Otps)){
				OtpDTOs=new ArrayList<Object>();
				for(SmsOtpDO otp:Otps){
					OtpDTOs.add(otpDAO.exportFromDB(otp));
				}
			}responseMessage.setStatusMessage("Otp data fetch success");
			responseMessage.setStatusCode(HttpStatusCodes.successCode);
			responseMessage.setObjectList(OtpDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorMsg+":fetching Otp data failed");
			responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
			responseMessage.setObjectList(OtpDTOs);
			return responseMessage;
		}

	}
	
	
	
	
}
