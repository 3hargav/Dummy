package com.incture.DAO;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.GuestLoginDO;
import com.incture.DTO.GuestLoginDTO;
import com.incture.util.HttpStatusCodes;
import com.incture.util.ServicesUtil;

@Repository
public class GuestLoginDAO extends BaseDao{

	public void addOtpDetails(GuestLoginDTO guestLoginDTO) {
		try{
			GuestLoginDO guestLoginDO = new GuestLoginDO();
			//if record already exists
			guestLoginDO=(GuestLoginDO) getSession().createQuery("select r from GuestLoginDO r where r.mobileNumber='"+guestLoginDTO.getMobileNumber()+"'").uniqueResult();
			if(!ServicesUtil.isEmpty(guestLoginDO))
				guestLoginDO.setOtp(guestLoginDTO.getOtp());
			else{//if new record 
				guestLoginDO=importToDB(guestLoginDTO);
			}
			getSession().saveOrUpdate(guestLoginDO);
		}catch(Exception e){
			
			System.out.println("GuestLoginDAO.addOtpdetails{}"+e.getMessage());
		}
		
	}

	private GuestLoginDO importToDB(GuestLoginDTO guestLoginDTO) {
		GuestLoginDO guestLoginDO= new GuestLoginDO();
		guestLoginDO.setMobileNumber(guestLoginDTO.getMobileNumber());
		guestLoginDO.setOtp(guestLoginDTO.getOtp());
		guestLoginDO.setVerified(HttpStatusCodes.notVerified);
		return guestLoginDO;
		
	}

	@SuppressWarnings({ "unchecked" })
	public String checkotp(GuestLoginDTO guestLoginDTO) {
		
		String strQuery="select s from GuestLoginDO s where s.mobileNumber='"+guestLoginDTO.getMobileNumber()+"'";
		System.err.println("strQuery:"+strQuery);
		Query<GuestLoginDO> query = getSession().createQuery(strQuery);
		GuestLoginDO guestLoginDO = query.uniqueResult();  
		System.err.println("guestLoginDo:"+guestLoginDO);
		if(ServicesUtil.isEqualsIgnoreCase(guestLoginDO.getOtp(),ServicesUtil.encodeString(guestLoginDTO.getOtp()))){
			guestLoginDO.setVerified(HttpStatusCodes.verified);
			return "Mobile validation succesfull";
		}
		else{
			guestLoginDO.setVerified(HttpStatusCodes.notVerified);
			return "Failed to Validate OTP";
		}
	}

}
