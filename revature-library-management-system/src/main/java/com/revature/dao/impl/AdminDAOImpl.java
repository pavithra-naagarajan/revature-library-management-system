package com.revature.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.dao.AdminDAO;
import com.revature.model.Admin;



@Repository
public class AdminDAOImpl implements AdminDAO {

	
	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Admin getAdminById(Long adminId) {
		Session session=sessionFactory.getCurrentSession();

		return session.get(Admin.class, adminId);
	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Admin> resultList=session.createQuery("select a from Admin a where a.adminName=?1").setParameter(1,adminName).getResultList();
		return (resultList.isEmpty()?null:resultList);	
		}


	@Override
	public List<Admin> getAdminByRole(String adminRole) {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Admin> resultList=session.createQuery("select a from Admin a where a.adminRole=?1").setParameter(1,adminRole).getResultList();
		return (resultList.isEmpty()?null:resultList);	
		}


	@Override
	public boolean isAdminExists(Long adminId) {
		Session session=sessionFactory.getCurrentSession();
		Admin admin = session.get(Admin.class, adminId);
		return (admin != null);
	}

	@Override
	public List<Admin> getAllAdmins() {
		Session session=sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<Admin> query=session.createQuery("select a from Admin a");
		return (query.getResultList().isEmpty()?null:query.getResultList());
	}

}
