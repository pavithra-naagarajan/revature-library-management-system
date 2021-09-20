package com.revature.librarymanagement.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.model.Admin;

import com.revature.librarymanagement.service.AdminService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	// get admin by role

	@GetMapping("role/{adminRole}")
	public ResponseEntity<List<Admin>> getAdminByRole(@PathVariable String adminRole) {
		List<Admin> admins =adminService.getAdminByRole(adminRole);
		return (CollectionUtils.isEmpty(admins)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(admins, HttpStatus.OK);

	}

	// get admin by name

	@GetMapping("Name/{adminName}")
	public ResponseEntity<List<Admin>> getAdminByName(@PathVariable String adminName) {
		List<Admin> admins =adminService.getAdminByName(adminName);
		return (CollectionUtils.isEmpty(admins)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(admins, HttpStatus.OK);
	}

	// get admin by adminId

	@GetMapping("/{adminId}")
	public ResponseEntity<Admin> getAdminByRole(@PathVariable Long adminId) {

		if (adminService.isAdminExists(adminId)) {
			Admin admin = adminService.getAdminById(adminId);
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else 

			throw new IdNotFoundException("Admin with:" + adminId + " Not Found!");

		
	}
	
	// get all admins

	@GetMapping
	public ResponseEntity<List<Admin>> getAllUsers() {
		List<Admin> admins =adminService.getAllAdmins();
		return (CollectionUtils.isEmpty(admins)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	// exception for Id not Found ..
		@ExceptionHandler(IdNotFoundException.class)

		public ResponseEntity<String> userNotFound(IdNotFoundException e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

}
