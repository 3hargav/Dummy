package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.EmailOtpDAO;
import com.incture.DO.EmailOtpDO;
import com.incture.DTO.EmailOtpDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.EmailOtpService;
import com.incture.util.HttpStatusCodes;
import com.incture.util.MailClass;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class EmailOtpServiceImpl implements EmailOtpService{
	@Autowired
	EmailOtpDAO emailOtpDAO;

	@Autowired
	MailClass mailClass;

	public String GenerateEmailOtp(int otpLength) {
		SplittableRandom splittableRandom = new SplittableRandom();

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < otpLength ; i++){
			sb.append(splittableRandom.nextInt(0,10));
		}
		return sb.toString();
	}



	public ResponseMessage addEmailOtpDetailsNew(EmailOtpDTO emailOtpDTO) {
		ResponseMessage responseMessage=new ResponseMessage();
		try{	
			if(!ServicesUtil.isEmpty(emailOtpDTO.getEmail())){
				String otp=ServicesUtil.GenerateOtp(6);
				emailOtpDTO.setEmailOtp(ServicesUtil.encodeString(otp));
				emailOtpDAO.addEmailOtpDetailsNew(emailOtpDTO);
				mailClass.sendOtp(otp, emailOtpDTO.getEmail());
				responseMessage.setStatusMessage("Otp sent successfully to Mail");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage(HttpStatusCodes.notFoundMsg+":-Received Null Object");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in addEmailotpdetailsNew-"+e.getMessage());
			responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorMsg+"-Sorry!! Failed:Exception in addEmailotpdetailsNew-"+e.getMessage());
			responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
			return responseMessage;
		}
	}

	public ResponseMessage validateEmailOtp(EmailOtpDTO emailOtpDTO) {

		ResponseMessage responseMessage=new ResponseMessage();

		try{
			if(ServicesUtil.isEmpty(ServicesUtil.isEmpty(emailOtpDTO.getEmailOtp()))){
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}else{
				String status=emailOtpDAO.validateEmailOtp(emailOtpDTO);
				System.err.println("status:"+status);
				responseMessage.setStatusMessage(status);
				responseMessage.setStatusCode(HttpStatusCodes.successCode);
				return responseMessage;
			}
		}catch(Exception e){
			System.err.println("Exception in EmailOtpserviceserviceImpl.validateEmailOtp():-"+e.getMessage());
			responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorMsg+"-Exception in EmailotpserviceImpl.validateEmailOtp():-"+e.getMessage());
			responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
			return responseMessage;
		}

	}



	public ResponseMessage fetchdata(){
		ResponseMessage responseMessage=new ResponseMessage();
		List<Object> emailOtpDTOs=null;
		try{
			List<EmailOtpDO> emailOtps=emailOtpDAO.getAllData();
			if(!ServicesUtil.isEmpty(emailOtps)){
				emailOtpDTOs=new ArrayList<Object>();
				for(EmailOtpDO emailotp:emailOtps){
					emailOtpDTOs.add(emailOtpDAO.exportFromDB(emailotp));
				}
			}responseMessage.setStatusMessage("emailOtp data fetch success");
			responseMessage.setStatusCode(HttpStatusCodes.successCode);
			responseMessage.setObjectList(emailOtpDTOs);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage(HttpStatusCodes.internalServerErrorMsg+":fetching emailOtp data failed");
			responseMessage.setStatusCode(HttpStatusCodes.internalServerErrorCode);
			responseMessage.setObjectList(emailOtpDTOs);
			return responseMessage;
		}

	}
}
