package com.incture.DAO;
import java.util.Base64;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.incture.DO.RegistrationDO;
import com.incture.DTO.RegistrationDTO;
import com.incture.DTO.SuperDTO;
import com.incture.DTO.loginDTO;
//import com.incture.entity.PassengerDO;
import com.incture.util.ServicesUtil;

@Repository
public class RegistrationDAO extends BaseDao {

	@Autowired
	ProductDAO productDAO;
	
	public RegistrationDO importToDB(RegistrationDTO registrationDTO){
		RegistrationDO registrationDO=new RegistrationDO();
		//CartDO cartDO = new CartDO();
		registrationDO.setFirstName(registrationDTO.getFirstname());
		registrationDO.setLastName(registrationDTO.getLastname());
		registrationDO.setContactNumber(registrationDTO.getContact());
		registrationDO.setGender(registrationDTO.getGender());
		registrationDO.setDateOfBirth(registrationDTO.getDob());
		registrationDO.setEmail(registrationDTO.getEmail());
		String encodedstring=Base64.getEncoder().encodeToString(registrationDTO.getPassword().getBytes());
		registrationDO.setPassword(encodedstring);
		//cartDO.setTotalPrice(0);
		//registrationDO.setCartdo(cartDO);
		return registrationDO;
	}

	public RegistrationDTO ExportFromDB(RegistrationDO registrationDO){
		RegistrationDTO registrationDTO=new RegistrationDTO();
		registrationDTO.setUserId(registrationDO.getUserId());
		registrationDTO.setFirstname(registrationDO.getFirstName());
		registrationDTO.setLastname(registrationDO.getLastName());
		registrationDTO.setContact(registrationDO.getContactNumber());
		registrationDTO.setGender(registrationDO.getGender());
		registrationDTO.setDob(registrationDO.getDateOfBirth());
		registrationDTO.setEmail(registrationDO.getEmail());
		String decodedPassword = new String(Base64.getDecoder().decode(registrationDO.getPassword()));
		registrationDTO.setPassword(decodedPassword);
		return registrationDTO;
	}

	public void addDetails(RegistrationDTO registrationDTO){
		RegistrationDO registrationDO=importToDB(registrationDTO);
		String encodedString =ServicesUtil.encodeString(registrationDTO.getPassword());
		registrationDO.setPassword(encodedString);
		Session session= getSession();
		session.save(registrationDO);
	}

	@SuppressWarnings({ "unchecked" })
	public String checkLogin(loginDTO loginDto) {
		String strQuery="select r from RegistrationDO r where r.contactNumber='"+loginDto.getUserName()+"'"
				+ " or r.email='"+loginDto.getUserName()+"'";
		System.err.println("strQuery:"+strQuery);
		Query<RegistrationDO> query = getSession().createQuery(strQuery);
		RegistrationDO registerDo=query.uniqueResult();
		System.err.println("registerDo:"+registerDo);
		String encodedString =ServicesUtil.encodeString(loginDto.getPassword());
		if(!ServicesUtil.isEmpty(registerDo) && registerDo.getPassword().equalsIgnoreCase(encodedString))
			return "Login Success";
		else
			return "Login Failed";
	}
	
	@SuppressWarnings("unchecked")
	public List<RegistrationDO> getAllData() {
		Session session= getSession();
		List<RegistrationDO> registrationList=session.createQuery("from RegistrationDO").list();
		return registrationList;

	}

	@SuppressWarnings("unchecked")
	public RegistrationDO getUserProfile(String userName) {
		Session session= getSession();
		Query<RegistrationDO> query = session.createQuery("select r from RegistrationDO r where r.contactNumber='"+userName+"'"
				+ " or r.email='"+userName+"'");
		RegistrationDO profile = query.uniqueResult();
		return profile;
	}

	public SuperDTO exportUserDetails(RegistrationDO userDetails) {

		SuperDTO supeNew = new SuperDTO();
		
		supeNew.setUserId(userDetails.getUserId());
		supeNew.setFirstname(userDetails.getFirstName());
		supeNew.setLastname(userDetails.getLastName());
		supeNew.setContact(userDetails.getContactNumber());
		supeNew.setGender(userDetails.getGender());
		supeNew.setDob(userDetails.getDateOfBirth());
		supeNew.setEmail(userDetails.getEmail());
		
		String decodedPassword = new String(Base64.getDecoder().decode(userDetails.getPassword()));
		supeNew.setPassword(decodedPassword);
			
		return supeNew;
	}

	@SuppressWarnings("unchecked")
	public String checkUser(String email_id) {
		String strQuery = "select r from RegistrationDO r where r.email='"+email_id+"'";
		System.err.println("strQuery:"+strQuery);
		Query<RegistrationDO> query = getSession().createQuery(strQuery);
		RegistrationDO registerDo = query.uniqueResult();
		if(!ServicesUtil.isEmpty(registerDo))
			return registerDo.getContactNumber();
		else
			return "Not found";
	}

}

		
		
		


