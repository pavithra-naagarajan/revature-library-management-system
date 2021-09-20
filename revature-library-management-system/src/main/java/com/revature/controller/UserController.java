
package com.revature.controller;

import java.util.List;

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

import com.revature.model.User;
import com.revature.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController

@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;

	// user login
	@GetMapping("login/{mailId}/{password}")
	public ResponseEntity<User> userLogin(@PathVariable("mailId") String mailId,

			@PathVariable("password") String password) {

		return new ResponseEntity<>(userService.userLogin(mailId, password), HttpStatus.OK);

	}

	// get user by id

	@GetMapping("{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {

		User user = new User();
		if (userService.isUserExists(userId)) {
			user = userService.getUserById(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// insert a user

	@PostMapping
	public ResponseEntity<String> addUser(@RequestBody User user) {

		long userId = user.getUserId();

		if (userService.isUserExists(userId))

			return new ResponseEntity<>(HttpStatus.CONFLICT);

		else

			return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);

	}

	// update a user

	@PutMapping
	public ResponseEntity<String> updateUser(@RequestBody User user) {

		long userId = user.getUserId();

		if (userService.isUserExists(userId))

			return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// delete a user

	@DeleteMapping("/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId) {

		if (userService.isUserExists(userId))

			return new ResponseEntity<>(userService.deleteUserById(userId), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	// get user by first and last name
	@GetMapping("/firstNameAndLastName/{firstName}/{lastName}")
	public ResponseEntity<User> getUserByFirstName(@PathVariable String firstName, @PathVariable String lastName) {

		return new ResponseEntity<>(userService.getUserByFirstAndLastName(firstName, lastName), HttpStatus.OK);
	}

	// get user by role
	@GetMapping("/role/{userRole}")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable String userRole) {

		return new ResponseEntity<>(userService.getUserByRole(userRole), HttpStatus.OK);
	}

	// get user by mobilenumber

	@GetMapping("/mobile/{mobileNumber}")
	public ResponseEntity<User> getUserByMobileNumber(@PathVariable String mobileNumber) {

		return new ResponseEntity<>(userService.getUserByMobileNumber(mobileNumber), HttpStatus.OK);
	}

	// get user by mailid
	@GetMapping("/mail/{mailId}")
	public ResponseEntity<User> getUserByMailId(@PathVariable String mailId) {

		return new ResponseEntity<>(userService.getUserByMailId(mailId), HttpStatus.OK);
	}

	// get all users

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {

		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

}
