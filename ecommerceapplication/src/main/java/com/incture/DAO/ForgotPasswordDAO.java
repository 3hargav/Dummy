package com.incture.DAO;

import org.springframework.stereotype.Repository;

import com.incture.DO.RegistrationDO;
import com.incture.DTO.ForgotPasswordDTO;
import com.incture.util.ServicesUtil;

@Repository
public class ForgotPasswordDAO extends BaseDao{

	public void SetNewPassword(ForgotPasswordDTO forgotPasswordDTO){
		try{
		RegistrationDO registrationDO=new RegistrationDO();
		
		registrationDO= (RegistrationDO) getSession().createQuery("select r from RegistrationDO r where r.email='"+forgotPasswordDTO.getEmail_id()+"'").uniqueResult();
  
		String encodedString =ServicesUtil.encodeString(forgotPasswordDTO.getPassword());
	    registrationDO.setPassword(encodedString);
	    
	    session.saveOrUpdate(registrationDO);
	    
		}catch(Exception e){
			System.out.println("error in ForgotPasswordDAO.SetNewPassword()"+e.getMessage());
		}
	}
	
	
	
	
}
