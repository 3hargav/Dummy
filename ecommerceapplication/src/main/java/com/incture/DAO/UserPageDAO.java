package com.incture.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.incture.DO.QueryDO;
import com.incture.DO.RegistrationDO;
import com.incture.DTO.QueryDTO;

@Repository
public class UserPageDAO extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public RegistrationDO getProfile(int userId) {
		Session session= getSession();
		Query<RegistrationDO> query = session.createQuery("select r from RegistrationDO r where r.userId="+userId+"");
		RegistrationDO profile = query.uniqueResult();
		return profile;		
		
	}

	public void saveToDB(RegistrationDO userOld) {
		Session session= getSession();
		session.save(userOld);
	}

	public void addQuery(QueryDTO queryDTO) {
		QueryDO queryDO = importToDBQuery(queryDTO);
		Session session= getSession();
		session.save(queryDO);
	}

	private QueryDO importToDBQuery(QueryDTO queryDTO) {
		QueryDO queryDO = new QueryDO();
		queryDO.setqDate(queryDTO.getQdate());
		queryDO.setUserId(queryDTO.getUserid());
		queryDO.setQuery(queryDTO.getQuery());
		queryDO.setAnswer(queryDTO.getAnswer());
		
		return queryDO;
	}

	@SuppressWarnings("unchecked")
	public List<QueryDO> getMyQueries(int userId) {
		Session session= getSession();
		List<QueryDO> queryList = session.createQuery("select s from QueryDO s where s.userId="+userId+"").list();
		return queryList;
	}

	public Object ExportFromDB(QueryDO qitem) {
		QueryDTO queryDTO = new QueryDTO();

		queryDTO.setQid(qitem.getQid());
		queryDTO.setUserid(qitem.getUserId());
		queryDTO.setQuery(qitem.getQuery());
		queryDTO.setAnswer(qitem.getAnswer());
		queryDTO.setQdate(qitem.getqDate());

		return queryDTO;
	}

	@SuppressWarnings("unchecked")
	public List<QueryDO> getAllQueries() {
		Session session= getSession();
		List<QueryDO> queryList = session.createQuery("from QueryDO").list();
		return queryList;
	}

	@SuppressWarnings("unchecked")
	public QueryDO getQueryId(int qid) {
		Session session= getSession();
		Query<QueryDO> query = session.createQuery("select r from QueryDO r where r.qid="+qid+"");
		QueryDO queryY = query.uniqueResult();
		return queryY;
	}

	public void saveQueryToDB(QueryDO queryOld) {
		Session session= getSession();
		session.save(queryOld);
	}
	
}
