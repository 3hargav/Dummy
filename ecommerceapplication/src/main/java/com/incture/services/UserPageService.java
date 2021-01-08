package com.incture.services;

import com.incture.DTO.QueryDTO;
import com.incture.DTO.RegistrationDTO;
import com.incture.response.ResponseMessage;

public interface UserPageService {

	ResponseMessage GetUserProfile(int userId);

	ResponseMessage editUser(RegistrationDTO registrationDTO, int userId);

	ResponseMessage addQuery(QueryDTO queryDTO, int userId);

	ResponseMessage getMyQueries(int userId);

	ResponseMessage answerQuery(QueryDTO queryDTO, int qid);

	ResponseMessage getAdminQueries();
	
}
