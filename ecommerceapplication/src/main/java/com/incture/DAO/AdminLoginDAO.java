package com.incture.DAO;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.incture.DO.AdminLoginDO;
import com.incture.DTO.AdminLoginDTO;
import com.incture.DTO.ChangeAdminLoginDTO;
import com.incture.util.ServicesUtil;

@Repository
public class AdminLoginDAO extends BaseDao {
	public AdminLoginDO importToDB(AdminLoginDTO adminLoginDTO){
		AdminLoginDO adminLoginDO=new AdminLoginDO();
		adminLoginDO.setEmail(adminLoginDTO.getEmail());
		String encodedstring=ServicesUtil.encodeString(adminLoginDTO.getPassword());
		adminLoginDO.setPassword(encodedstring);
		return adminLoginDO;
	}

	public AdminLoginDTO ExportFromDB(AdminLoginDO adminLoginDO){
		AdminLoginDTO adminLoginDTO=new AdminLoginDTO();
		adminLoginDTO.setEmail(adminLoginDO.getEmail());
		adminLoginDTO.setPassword(adminLoginDO.getPassword());
		adminLoginDTO.setId(adminLoginDO.getAdminId());
		return adminLoginDTO;
	}

	public void addAdminDetails(AdminLoginDTO adminLoginDTO){
		AdminLoginDO adminLoginDO=importToDB(adminLoginDTO);
		Session session= getSession();
		session.save(adminLoginDO);
	}

	@SuppressWarnings({ "unchecked" })
	public String checkAdminLogin(AdminLoginDTO adminLoginDTO) {
		String strQuery="select r from AdminLoginDO r where r.email='"+adminLoginDTO.getEmail()+"'";
		System.err.println("strQuery:"+strQuery);
		Query<AdminLoginDO> query = getSession().createQuery(strQuery);
		AdminLoginDO adminLoginDO=query.uniqueResult();
		System.err.println("AdminLoginDO:"+adminLoginDO);
		String encodedString =ServicesUtil.encodeString(adminLoginDTO.getPassword());
		if(!ServicesUtil.isEmpty(adminLoginDO) && adminLoginDO.getPassword().equalsIgnoreCase(encodedString))
			return "Login Success";
		else
			return "Login Failed";
	}
	
	@SuppressWarnings("unchecked")
	public List<AdminLoginDO> getAllData() {
		Session session= getSession();
		List<AdminLoginDO> adminLoginList=session.createQuery("from AdminLoginDO").list();
		return adminLoginList;

}

	public void modify_admin_email(AdminLoginDTO adminLoginDTO, ChangeAdminLoginDTO changeAdminLoginDTO){
		
		try{
			AdminLoginDO adminLoginDO=new AdminLoginDO();
			adminLoginDO=(AdminLoginDO) getSession().createQuery("select e from AdminLoginDO e where e.email='"+adminLoginDTO.getEmail()+"'").uniqueResult();
			if(!ServicesUtil.isEmpty(adminLoginDO))
				adminLoginDO.setEmail(changeAdminLoginDTO.getNewemail());
			else{
				System.out.println("Admin Record does not exist");
			}
			getSession().saveOrUpdate(adminLoginDO);
		}catch(Exception e){
			
			System.out.println("AdminLoginDAO.modify_admin_email{}"+e.getMessage());
		}

		
	}
}
