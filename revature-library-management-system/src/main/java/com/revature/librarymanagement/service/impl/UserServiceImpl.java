
package com.revature.librarymanagement.service.impl;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.UserDAO;
import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.mapper.UserMapper;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public String addUser(UserDto userDto) {
		User user=UserMapper.dtoToEntity(userDto);
		return userDAO.addUser(user);

	}

	@Override
	public String updateUser(UserDto userDto) {
		User user=UserMapper.dtoToEntity(userDto);
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

	@Override
	public User userLogin(String mailId, String password) {
		return userDAO.userLogin(mailId,password);
	}

}
