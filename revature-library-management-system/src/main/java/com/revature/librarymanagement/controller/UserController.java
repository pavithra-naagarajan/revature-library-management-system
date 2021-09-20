
package com.revature.librarymanagement.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.model.User;
import com.revature.librarymanagement.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController

@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	// user login
	@GetMapping("login/{mailId}/{password}")
	public ResponseEntity<String> userLogin(@PathVariable("mailId") String mailId,

			@PathVariable("password") String password) {
		User user = userService.userLogin(mailId, password);
		String message = "Login finished successfully!";
		return (user != null) ? new ResponseEntity<>(message, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// get user by id

	@GetMapping("{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {

		
		if (userService.isUserExists(userId)) {
			User user = userService.getUserById(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else 

			throw new IdNotFoundException("User with:" + userId + " Not Found!");

		
	}

	// insert a user

	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {

		Long userId = userDto.getUserId();
		String mailId = userDto.getMailId();

		if (userService.isUserExists(userId)) {
			throw new DuplicateIdException("User account with Id already exists!");

		}

		if (userService.getUserByMailId(mailId) != null) {

			throw new DuplicateIdException("User account already exists with same details!");

		} else

			return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);

	}

	// update a user

	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody UserDto userDto) {

		long userId = userDto.getUserId();

		if (userService.isUserExists(userId))

			return new ResponseEntity<>(userService.updateUser(userDto), HttpStatus.OK);
		else 

			throw new IdNotFoundException("User Id:" + userId + " Not Found to Update!");

		
	}

	// delete a user

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {

		if (userService.isUserExists(userId))

			return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
		else {

			throw new IdNotFoundException("User Id:" + userId + " Not Found to delete!");
		}
	}

	// get user by first and last name
	@GetMapping("/firstNameAndLastName/{firstName}/{lastName}")
	public ResponseEntity<User> getUserByFirstAndLastName(@PathVariable String firstName,
			@PathVariable String lastName) {

		User user = userService.getUserByFirstAndLastName(firstName, lastName);
		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// get user by role
	@GetMapping("/role/{userRole}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String userRole) {
		List<User> users = userService.getUserByRole(userRole);
		return (CollectionUtils.isEmpty(users)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(users, HttpStatus.OK);
	}

	// get user by mobilenumber

	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<User> getUserByMobileNumber(@PathVariable String mobileNumber) {

		User user = userService.getUserByMobileNumber(mobileNumber);
		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// get user by mailid
	@GetMapping("/mail/{mailId}")
	public ResponseEntity<User> getUserByMailId(@PathVariable String mailId) {

		User user = userService.getUserByMailId(mailId);
		return (user != null) ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// get all users

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		List<User> users = userService.getAllUsers();
		return (CollectionUtils.isEmpty(users)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(users, HttpStatus.OK);

	}

	// exception for Duplicate Id insertion..
	@ExceptionHandler(DuplicateIdException.class)

	public ResponseEntity<String> duplicateIdFound(DuplicateIdException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	// exception for Id not Found ..
	@ExceptionHandler(IdNotFoundException.class)

	public ResponseEntity<String> userNotFound(IdNotFoundException e) {

		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}
