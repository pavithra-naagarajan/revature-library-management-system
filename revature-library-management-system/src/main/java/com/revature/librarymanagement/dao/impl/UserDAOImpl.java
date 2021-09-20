
package com.revature.librarymanagement.dao.impl;

import java.time.LocalDateTime;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.librarymanagement.dao.UserDAO;
import com.revature.librarymanagement.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	static final LocalDateTime localTime = LocalDateTime.now();

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public String addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Long userId = null;
		try {

			session.save(user);
			userId = user.getUserId();
		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "User Account created with : " + userId + " at " + localTime;
	}

	@Transactional
	@Override
	public String updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();

		try {

			session.merge(user);

		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "User details updated successfully!";
	}

	@Transactional
	@Override
	public String deleteUserById(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = getUserById(userId);
		try {

			session.delete(user);
		} catch (HibernateException e1) {

			e1.printStackTrace();
		}

		return "User Account deleted with : " + userId + " at " + localTime;
	}

	@Override
	public User getUserById(Long userId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(User.class, userId);
	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> resultList = session.createQuery("select u from User u where u.mobileNumber=?1")
				.setParameter(1, mobileNumber).getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

	@Override
	public User getUserByMailId(String mailId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> resultList = session.createQuery("select u from User u where u.mailId=?1").setParameter(1, mailId)
				.getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

	@Override
	public List<User> getUserByRole(String userRole) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> resultList = session.createQuery("select u from User u where u.userRole=?1")
				.setParameter(1, userRole).getResultList();
		return (resultList.isEmpty() ? null : resultList);
	}

	@Override
	public boolean isUserExists(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, userId);
		return (user != null);
	}

	@Override
	public List<User> getAllUsers() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery("select u from User u");
		return (query.getResultList().isEmpty() ? null : query.getResultList());
	}

	@Override
	public User getUserByFirstAndLastName(String firstName, String lastName) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> resultList = session.createQuery("select u from User u where u.firstName=?1 and u.lastName=?2")
				.setParameter(1, firstName).setParameter(2, lastName).getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

	@Override
	public User userLogin(String mailId, String password) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> resultList = session.createQuery("select u from User u where u.mailId=?1 and u.password=?2")
				.setParameter(1, mailId).setParameter(2, password).getResultList();
		return (resultList.isEmpty() ? null : resultList.get(0));
	}

}
