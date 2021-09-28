
package com.revature.librarymanagement.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.revature.librarymanagement.controller.MailSend;
import com.revature.librarymanagement.dao.UserDAO;
import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.UserMapper;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.UserService;
import com.revature.librarymanagement.util.PasswordGenerator;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	@Override
	public String addUser(UserDto userDto) {
		logger.info("Entering add user Function");

		User user = UserMapper.dtoToEntity(userDto);
		Long userId = user.getUserId();
		String mailId = user.getMailId();
		String mobileNumber = user.getMobileNumber();
		if (userDAO.isUserExists(userId)) {
			throw new DuplicateIdException("User account with Id:" + userId + " already exists!");

		}

		if (userDAO.getUserByMailId(mailId) != null || userDAO.getUserByMobileNumber(mobileNumber) != null) {

			throw new DuplicateIdException("User account already exists with same details!");

		}

		MailSend.sendMail(user.getMailId(), "User Registration :",
				"Hi, " + user.getFirstName() + "\nYour account is created successfully!\n\nThank You.");

		return userDAO.addUser(user);

	}

	@Override
	public String updateUser(UserDto userDto) {
		logger.info("Entering update user Function");

		User user = UserMapper.dtoToEntity(userDto);
		Long userId = user.getUserId();

		if (userDAO.isUserExists(userId))
			return userDAO.updateUser(user);

		else
			throw new IdNotFoundException("User with Id:" + userId + " Not Found to Update!");

	}

	@Override
	public String deleteUserById(Long userId) {
		logger.info("Entering delete user Function");

		if (userDAO.isUserExists(userId))

			return userDAO.deleteUserById(userId);
		else

			throw new IdNotFoundException("User Id:" + userId + " Not Found to delete!");

	}

	@Override
	public User getUserById(Long userId) {
		logger.info("Entering get User By Id Function");

		if (userDAO.isUserExists(userId))
			return userDAO.getUserById(userId);
		else

			throw new IdNotFoundException("User with Id:" + userId + " Not Found!");

	}

	@Override
	public List<User> getUserByFirstAndLastName(String name) {
		logger.info("Entering get User By FirstAndLastName Function");

		List<User> users = userDAO.getUserByFirstAndLastName(name);
		if (CollectionUtils.isEmpty(users))
			throw new NullValueException("No records Found");
		return users;
	}

	@Override
	public User getUserByMobileNumber(String mobileNumber) {
		logger.info("Entering get User By mobile number Function");

		User user = userDAO.getUserByMobileNumber(mobileNumber);
		if (user == null)
			throw new NullValueException("No record Found");

		return user;
	}

	@Override
	public User getUserByMailId(String mailId) {
		logger.info("Entering get User By mailId Function");

		User user = userDAO.getUserByMailId(mailId);
		if (user == null)
			throw new NullValueException("No record Found");
		else
			return user;
	}

	@Override
	public List<User> getUserByRole(String userRole) {
		logger.info("Entering get User By user role Function");

		List<User> users = userDAO.getUserByRole(userRole);
		if (CollectionUtils.isEmpty(users))
			throw new NullValueException("No records Found");
		return users;
	}

	@Override
	public boolean isUserExists(Long userId) {
		logger.info("Entering is User Exists Function");

		return userDAO.isUserExists(userId);
	}

	@Override
	public List<User> getAllUsers() {
		logger.info("Entering get all users Function");

		List<User> users = userDAO.getAllUsers();
		if (CollectionUtils.isEmpty(users))
			throw new NullValueException("No records Found");
		return users;
	}

	@Override
	public User userLogin(String mailId, String password) {
		logger.info("Entering user login Function");

		User user = userDAO.userLogin(mailId, password);
		if (user == null)
			throw new NullValueException("No record Found");
		return user;
	}

	@Override
	public String forgotPassword(String mailId) {
		logger.info("Entering forgot password Function");

		User user = userDAO.getUserByMailId(mailId);
		if (user != null)
			return userDAO.forgotPassword(mailId, PasswordGenerator.generatePassword());

		else
			throw new IdNotFoundException("User Not Found!");

	}

}
