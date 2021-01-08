package com.incture.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class BaseDao {

	@Autowired
	SessionFactory sessionFactory;	 
	Session session;
	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			System.err.println("[BaseDao] " + e.getMessage());
			return sessionFactory.openSession();
		}} 

}
