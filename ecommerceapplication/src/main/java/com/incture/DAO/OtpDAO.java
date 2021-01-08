package com.incture.DAO;

import java.io.IOException;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.incture.DO.SmsOtpDO;
import com.incture.DTO.SmsOtpDTO;
import com.incture.util.HttpStatusCodes;
import com.incture.util.ServicesUtil;

@Repository
public class OtpDAO extends BaseDao{

	public SmsOtpDO importToDB(SmsOtpDTO otpdto) throws IOException{
		SmsOtpDO otpDO= new SmsOtpDO();
		otpDO.setMobileNumber(otpdto.getMobile_number());
		otpDO.setOtp(otpdto.getOtp());
		otpDO.setVerified(HttpStatusCodes.notVerified);
		return otpDO;
		
	}
	
	public SmsOtpDTO exportFromDB(SmsOtpDO smsOtpdo){
		SmsOtpDTO otpDTO=new SmsOtpDTO();
		otpDTO.setMobile_number(smsOtpdo.getMobileNumber());
		//otpDTO.setOtp(otpdo.getOtp());
		return otpDTO;
		
	}
	
	public void addOtpDetails(SmsOtpDTO smsOtpDTO) throws IOException{
		try{
			SmsOtpDO otpDO=new SmsOtpDO();
			//if record already exists
			otpDO=(SmsOtpDO) getSession().createQuery("select e from SmsOtpDO e where e.mobileNumber='"+smsOtpDTO.getMobile_number()+"'").uniqueResult();
			if(!ServicesUtil.isEmpty(otpDO))
				otpDO.setOtp(smsOtpDTO.getOtp());
			else{//if new record 
				otpDO=importToDB(smsOtpDTO);
			}
			getSession().saveOrUpdate(otpDO);
		}catch(Exception e){
			
			System.out.println("OtpDAO.addOtpdetails{}"+e.getMessage());
		}
		
	}
	
	

	@SuppressWarnings({ "unchecked" })
	public String checkotp(SmsOtpDTO otpdto){
		String strQuery="select s from SmsOtpDO s where s.mobileNumber='"+otpdto.getMobile_number()+"'";
		System.err.println("strQuery:"+strQuery);
		Query<SmsOtpDO> query = getSession().createQuery(strQuery);
		SmsOtpDO otpDO=query.uniqueResult();  
		System.err.println("otpDo:"+otpDO);
		if(ServicesUtil.isEqualsIgnoreCase(otpDO.getOtp(),ServicesUtil.encodeString(otpdto.getOtp()))){
			otpDO.setVerified(HttpStatusCodes.verified);
			return "Mobile validation succesfull";
		}
		else{
			otpDO.setVerified(HttpStatusCodes.notVerified);
			return "Failed to Validate OTP";
		}
	}

	@SuppressWarnings("unchecked")
	public List<SmsOtpDO> getAllData() {
		Session session= getSession();
		List<SmsOtpDO> OtpList=session.createQuery("from SmsOtpDO").list();
		return OtpList;

}


}
