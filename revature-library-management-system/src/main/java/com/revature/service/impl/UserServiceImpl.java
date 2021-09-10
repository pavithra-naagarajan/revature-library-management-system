
package com.revature.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.UserDAO;
import com.revature.model.User;
import com.revature.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public String addUser(User user) {
		return userDAO.addUser(user);

	}

	@Override
	public String updateUser(User user) {
		return userDAO.updateUser(user);
	}

	@Override
	public String deleteUserById(Long userId) {
		return userDAO.deleteUserById(userId);

	}

	@Override
	public User getUserById(Long userId) {
		return userDAO.getUserById(userId);

	}

	@Override
	public User getUserByFirstAndLastName(String firstName, String lastName) {
		return userDAO.getUserByFirstAndLastName(firstName, lastName);

	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		return userDAO.getUserByMobileNumber(mobileNumber);
	}

	@Override
	public User getUserByMailId(String mailId) {
		return userDAO.getUserByMailId(mailId);
	}

	@Override
	public List<User> getUserByRole(String userRole) {
		return  userDAO.getUserByRole(userRole);
	}

	@Override
	public boolean isUserExists(Long userId) {
		User user = userDAO.getUserById(userId);
		return (user != null);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();

	}

}
