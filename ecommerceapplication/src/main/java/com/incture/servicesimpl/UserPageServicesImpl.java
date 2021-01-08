package com.incture.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.incture.DAO.RegistrationDAO;
import com.incture.DAO.UserPageDAO;
import com.incture.DO.QueryDO;
import com.incture.DO.RegistrationDO;
import com.incture.DTO.QueryDTO;
import com.incture.DTO.RegistrationDTO;
import com.incture.response.ResponseMessage;
import com.incture.services.UserPageService;
import com.incture.util.ServicesUtil;

@Service
@Transactional
public class UserPageServicesImpl implements UserPageService {

	@Autowired
	UserPageDAO userPageDAO;
	@Autowired
	RegistrationDAO registrationDAO;
	
	public ResponseMessage GetUserProfile(int userId){
		ResponseMessage responseMessage=new ResponseMessage();
		RegistrationDTO registrationDTO=new RegistrationDTO();
		try{
			RegistrationDO profile = userPageDAO.getProfile(userId);
	        if(!ServicesUtil.isEmpty(profile)){
	        registrationDTO=registrationDAO.ExportFromDB(profile);
			responseMessage.setStatusMessage("fetch success");
			responseMessage.setStatusCode(200);
			responseMessage.setObject(registrationDTO);
			return responseMessage;
			}
	        else{
	        	responseMessage.setStatusMessage("No record found");
				responseMessage.setStatusCode(200);
				return responseMessage;
	        }
		}catch(Exception e){
			responseMessage.setStatusMessage("fetching failed");
			responseMessage.setStatusCode(500);
			//responseMessage.setObject(registrationDTOs);
			return responseMessage;
		}
			
	}

	public ResponseMessage editUser(RegistrationDTO registrationDTO, int userId) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(!ServicesUtil.isEmpty(registrationDTO)){
				RegistrationDO userOld = userPageDAO.getProfile(userId);
		
				userOld.setFirstName(registrationDTO.getFirstname());
				userOld.setLastName(registrationDTO.getLastname());
				userOld.setDateOfBirth(registrationDTO.getDob());
				userOld.setGender(registrationDTO.getGender());
				userOld.setUserId(registrationDTO.getUserId());
				userOld.setEmail(registrationDTO.getEmail());
				userOld.setContactNumber(registrationDTO.getContact());

				userPageDAO.saveToDB(userOld);
			}

			responseMessage.setStatusMessage("Successfully edited user");
			responseMessage.setStatusCode(200);
			return responseMessage;	
		}
		catch(Exception e){
			System.err.println("Exception in userPageServiceImpl.edituser():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in userPageServiceImpl.edituser():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage addQuery(QueryDTO queryDTO, int userId) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			
			if(!ServicesUtil.isEmpty(queryDTO)){
				userPageDAO.addQuery(queryDTO);
				responseMessage.setStatusMessage("Successfully added query");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}else{
				responseMessage.setStatusMessage("Received Null Object");
				responseMessage.setStatusCode(200);
				return responseMessage;
			}

		}catch(Exception e){
			System.err.println("Exception in userPageServiceImpl.addQuery():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in userPageServiceImpl.addQuery():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage getMyQueries(int userId) {
		
		ResponseMessage responseMessage = new ResponseMessage();
		List<Object> queryDtos = null;
		try{
			List<QueryDO> queries = userPageDAO.getMyQueries(userId);
			if(!ServicesUtil.isEmpty(queries)){
				queryDtos = new ArrayList<Object>();
				for(QueryDO qitem:queries){
					queryDtos.add(userPageDAO.ExportFromDB(qitem));
				}
			}responseMessage.setStatusMessage("All my queries fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(queryDtos);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All my queries fetching failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(queryDtos);
			return responseMessage;
		}
	}

	public ResponseMessage answerQuery(QueryDTO queryDTO, int qid) {
		
		ResponseMessage responseMessage=new ResponseMessage();
		try{
			if(!ServicesUtil.isEmpty(queryDTO)){
				QueryDO queryOld = userPageDAO.getQueryId(qid);		
				queryOld.setAnswer(queryDTO.getAnswer());
				userPageDAO.saveQueryToDB(queryOld);
			}
		responseMessage.setStatusMessage("Successfully updated query");
		responseMessage.setStatusCode(200);
		return responseMessage;	
		}
		catch(Exception e){
			System.err.println("Exception in UserPageServicesImpl.answerQuery():-"+e.getMessage());
			responseMessage.setStatusMessage("Sorry!! Failed:Exception in UserPageServicesImpl.answerQuery():-"+e.getMessage());
			responseMessage.setStatusCode(500);
			return responseMessage;
		}
	}

	public ResponseMessage getAdminQueries() {
		
		ResponseMessage responseMessage = new ResponseMessage();
		List<Object> queryDtos = null;
		try{
			List<QueryDO> queries = userPageDAO.getAllQueries();
			if(!ServicesUtil.isEmpty(queries)){
				queryDtos = new ArrayList<Object>();
				for(QueryDO qitem:queries){
					queryDtos.add(userPageDAO.ExportFromDB(qitem));
				}
			}responseMessage.setStatusMessage("All admin queries fetched successfully");
			responseMessage.setStatusCode(200);
			responseMessage.setObjectList(queryDtos);
			return responseMessage;
		}catch(Exception e){
			responseMessage.setStatusMessage("All admin queries fetching failed");
			responseMessage.setStatusCode(500);
			responseMessage.setObjectList(queryDtos);
			return responseMessage;
		}
	}
	
}
