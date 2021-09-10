
package com.revature.service;

import java.util.List;

import com.revature.model.User;

public interface UserService {
	public String addUser(User user);

	public String updateUser(User user);

	public String deleteUserById(Long userId);

	public User getUserById(Long userId);

	public User getUserByFirstAndLastName(String firstName, String lastName);

	public User getUserByMobileNumber(String mobileNumber);

	public User getUserByMailId(String mailId);

	public List<User> getUserByRole(String userRole);

	public boolean isUserExists(Long userId);

	public List<User> getAllUsers();

}
