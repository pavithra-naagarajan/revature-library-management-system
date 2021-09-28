package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;


import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.controller.MailSend;
import com.revature.librarymanagement.dao.AdminDAO;
import com.revature.librarymanagement.exception.DatabaseException;
import com.revature.librarymanagement.model.Admin;
import com.revature.librarymanagement.util.PasswordGenerator;

@Repository
public class AdminDAOImpl implements AdminDAO {
	private static final Logger logger = LogManager.getLogger(AdminDAOImpl.class);

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	private static final String GET_ADMIN_BY_NAME = "select a from Admin a where a.adminName=?1";
	private static final String GET_ADMIN_BY_ROLE = "select a from Admin a where a.adminRole=?1";
	private static final String GET_ALL_ADMINS = "select a from Admin a";
	private static final String ADMIN_LOGIN = "select a from Admin a where a.adminId=?1 and a.adminPassword=?2";

	@Override
	public Admin getAdminById(Long adminId) {
		logger.info("Entering get Admin By Id Function");

		Session session = sessionFactory.getCurrentSession();

		return session.get(Admin.class, adminId);
	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		logger.info("Entering get Admin By name Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<Admin> resultList = session.createQuery(GET_ADMIN_BY_NAME, Admin.class).setParameter(1, adminName)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from the DataBase");
		}
	}

	@Override
	public List<Admin> getAdminByRole(String adminRole) {
		logger.info("Entering get Admin By role Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<Admin> resultList = session.createQuery(GET_ADMIN_BY_ROLE, Admin.class).setParameter(1, adminRole)
					.getResultList();
			return (resultList.isEmpty() ? null : resultList);
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Override
	public boolean isAdminExists(Long adminId) {
		logger.info("Entering is admin exists Function");

		Session session = sessionFactory.getCurrentSession();
		Admin admin = session.get(Admin.class, adminId);
		return (admin != null);
	}

	@Override
	public List<Admin> getAllAdmins() {
		logger.info("Entering get all admins Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			Query<Admin> query = session.createQuery(GET_ALL_ADMINS, Admin.class);
			return (query.getResultList().isEmpty() ? null : query.getResultList());
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

	@Transactional
	@Override
	public String deleteAdminById(Long adminId) {
		logger.info("Entering delete Admin By Id Function");

		Admin admin = getAdminById(adminId);
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(admin);
			return "Admin Account deleted with : " + adminId + " at " + localTime;

		} catch (Exception e) {
			throw new DatabaseException("Error in deleting admin from DataBase");
		}

	}

	@Transactional
	@Override
	public String addAdmin(Admin admin) {
		logger.info("Entering add Admin Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			admin.setAdminPassword(PasswordGenerator.generatePassword());
			session.save(admin);
			Long adminId = admin.getAdminId();
			MailSend.sendMail(admin.getMailId(), "Admin Registration :",
					"Hi, " + admin.getAdminName()
							+ "\nSuper Admin registered your profile.Use this login credentials to login further!"
							+ "\nAdmin Id :" + admin.getAdminId() + "\nNew Password :" + admin.getAdminPassword()
							+ "\n\nThank You.");
			return "Admin Account created with : " + adminId + " at " + localTime;

		} catch (Exception e) {
			throw new DatabaseException("Error in adding admin to DataBase");
		}

	}

	@Transactional
	@Override
	public String updateAdmin(Admin admin) {
		logger.info("Entering update Admin  Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			session.merge(admin);
			return "Admin details updated successfully!";

		} catch (Exception e) {
			throw new DatabaseException("Error in updating admin to DataBase");
		}

	}

	@Override
	public Admin adminLogin(Long adminId, String adminPassword) {
		logger.info("Entering admin login Function");

		try {
			Session session = sessionFactory.getCurrentSession();
			List<Admin> resultList = session.createQuery(ADMIN_LOGIN, Admin.class).setParameter(1, adminId)
					.setParameter(2, adminPassword).getResultList();
			return (resultList.isEmpty() ? null : resultList.get(0));
		} catch (Exception e) {
			throw new DatabaseException("Error in fetching data from DataBase");
		}
	}

}
