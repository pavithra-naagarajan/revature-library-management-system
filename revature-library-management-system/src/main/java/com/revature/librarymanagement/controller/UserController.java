
package com.revature.librarymanagement.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.UserDto;

import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.UserService;

@CrossOrigin(origins = "*")

@RestController

@RequestMapping("user")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserService userService;

	// user login
	@GetMapping("login/{mailId}/{password}")
	public ResponseEntity<User> userLogin(@PathVariable("mailId") String mailId,
			@PathVariable("password") String password) {
		logger.info("Entering user login Function");

		return new ResponseEntity<>(userService.userLogin(mailId, password), HttpStatus.OK);

	}

	// get user by id
	@GetMapping("{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
		logger.info("Entering get user by Id Function");

		return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);

	}

	// insert a user
	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
		logger.info("Entering add user Function");

		return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
	}

	// update a user
	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {
		logger.info("Entering update user Function");

		return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);

	}

	// delete a user
	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {
		logger.info("Entering delete user Function");

		return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);

	}

	// get user by first and last name
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> getUserByFirstAndLastName(@PathVariable String name) {
		logger.info("Entering get user by name Function");

		return new ResponseEntity<>(userService.getUserByFirstAndLastName(name), HttpStatus.OK);
	}

	// get user by role
	@GetMapping("/role/{userRole}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String userRole) {
		logger.info("Entering get user by role Function");

		return new ResponseEntity<>(userService.getUserByRole(userRole), HttpStatus.OK);
	}

	// get user by mobilenumber
	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<User> getUserByMobileNumber(@PathVariable String mobileNumber) {
		logger.info("Entering get user by mobile number Function");

		return new ResponseEntity<>(userService.getUserByMobileNumber(mobileNumber), HttpStatus.OK);
	}

	// get user by mailid
	@GetMapping("/mail/{mailId}")
	public ResponseEntity<User> getUserByMailId(@PathVariable String mailId) {
		logger.info("Entering get user by mailId Function");

		return new ResponseEntity<>(userService.getUserByMailId(mailId), HttpStatus.OK);

	}

	// get all users
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		logger.info("Entering get all users Function");

		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

	}

	@PutMapping("/forgotpassword/{mailId}")
	public ResponseEntity<String> forgotPassword(@PathVariable String mailId) {
		logger.info("Entering forgot password Function");

		return new ResponseEntity<>(userService.forgotPassword(mailId), HttpStatus.OK);

	}

}
