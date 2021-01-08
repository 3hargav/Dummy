package com.incture.DAO;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.EmailOtpDO;
import com.incture.DTO.EmailOtpDTO;
import com.incture.util.HttpStatusCodes;
import com.incture.util.ServicesUtil;

@Repository
public class EmailOtpDAO extends BaseDao{
	public String otp;

	//EmailOtpService emailOtpService;

	public EmailOtpDO importToDB(EmailOtpDTO emailOtpDTO) throws IOException {

		EmailOtpDO emailOtpDO = new EmailOtpDO();
		emailOtpDO.setEmail(emailOtpDTO.getEmail());
		emailOtpDO.setOtp(emailOtpDTO.getEmailOtp());

		emailOtpDO.setVarified(Boolean.FALSE);
return emailOtpDO;

	}

	public EmailOtpDTO exportFromDB(EmailOtpDO emailOtpDO){
		EmailOtpDTO emailOtpDTO = new EmailOtpDTO();
		emailOtpDTO.setEmail(emailOtpDO.getEmail());
		emailOtpDTO.setEmailOtp(emailOtpDO.getOtp());
		return emailOtpDTO;
	}


	public void addEmailOtpDetailsNew(EmailOtpDTO emailOtpDTO) {
		try{
			EmailOtpDO emailOtpDO = new EmailOtpDO();
			//if record already exists
			emailOtpDO= (EmailOtpDO) getSession().createQuery("select e from EmailOtpDO e where e.email='"+emailOtpDTO.getEmail()+"'").uniqueResult();
			if(!ServicesUtil.isEmpty(emailOtpDO))
				emailOtpDO.setOtp(emailOtpDTO.getEmailOtp());
			else{//if new record 
				emailOtpDO = importToDB(emailOtpDTO);
			}
			getSession().saveOrUpdate(emailOtpDO);
			
		}catch(Exception e){
			System.out.println("EmailOtpDAO.addEmailOtpDetails: {}"+e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public String validateEmailOtp(EmailOtpDTO emailOtpDTO){
		try{

			String strQuery = "select s from EmailOtpDO s where s.email ='"+emailOtpDTO.getEmail()+"'";
			Query<EmailOtpDO> query = getSession().createQuery(strQuery);
			EmailOtpDO emailOtpDO = query.uniqueResult();
			if(ServicesUtil.isEqualsIgnoreCase(ServicesUtil.encodeString(emailOtpDTO.getEmailOtp()),emailOtpDO.getOtp())){
				emailOtpDO.setVarified(HttpStatusCodes.verified);
				getSession().saveOrUpdate(emailOtpDO);
				return "Mail Validation Success";
			}
			else {
				emailOtpDO.setVarified(HttpStatusCodes.notVerified);
				getSession().saveOrUpdate(emailOtpDO);
				return "Mail Validation Failed";
			}

		}catch(Exception e){
			System.err.println("validateEmailOtp():"+e.getMessage());
			return "Couldn't Validate Mail or Already Validated";
		}

	}
	@SuppressWarnings("unchecked")
	public List<EmailOtpDO> getAllData() {
		Session session= getSession();
		List<EmailOtpDO> emailOtpList=session.createQuery("from EmailOtpDO").list();
		return emailOtpList;

}
}

