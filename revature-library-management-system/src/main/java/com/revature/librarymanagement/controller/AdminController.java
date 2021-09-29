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

import com.revature.librarymanagement.dto.AdminDto;

import com.revature.librarymanagement.model.Admin;
import com.revature.librarymanagement.service.AdminService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	// admin login
	@GetMapping("login/{adminId}/{adminPassword}")
	public ResponseEntity<Admin> adminLogin(@PathVariable("adminId") Long adminId,
			@PathVariable("adminPassword") String adminPassword) {

		logger.info("Entering Admin Login Function");
		return new ResponseEntity<>(adminService.adminLogin(adminId, adminPassword), HttpStatus.OK);

	}

	// insert admin
	@PostMapping
	public ResponseEntity<String> addAdmin(@RequestBody AdminDto adminDto) {
		logger.info("Entering Add Admin Function");
		return new ResponseEntity<>(adminService.addAdmin(adminDto), HttpStatus.OK);

	}

	// update admin

	@PutMapping
	public ResponseEntity<String> updateAdmin(@RequestBody AdminDto adminDto) {
		logger.info("Entering Update Admin Function");
		return new ResponseEntity<>(adminService.updateAdmin(adminDto), HttpStatus.OK);

	}
	// delete admin

	@DeleteMapping("/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable("adminId") Long adminId) {
		logger.info("Entering Delete Admin Function");

		return new ResponseEntity<>(adminService.deleteAdminById(adminId), HttpStatus.OK);

	}
	// get admin by role

	@GetMapping("role/{adminRole}")
	public ResponseEntity<List<Admin>> getAdminByRole(@PathVariable String adminRole) {
		logger.info("Entering Get Admin By Role Function");

		return new ResponseEntity<>(adminService.getAdminByRole(adminRole), HttpStatus.OK);

	}

	// get admin by name

	@GetMapping("name/{adminName}")
	public ResponseEntity<List<Admin>> getAdminByName(@PathVariable String adminName) {
		logger.info("Entering Get Admin By Name Function");

		return new ResponseEntity<>(adminService.getAdminByName(adminName), HttpStatus.OK);
	}

	// get admin by adminId

	@GetMapping("/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {
		logger.info("Entering Get Admin By Id Function");

		return new ResponseEntity<>(adminService.getAdminById(adminId), HttpStatus.OK);

	}

	// get all admins

	@GetMapping
	public ResponseEntity<List<Admin>> getAllAdmins() {
		logger.info("Entering Get All Admins Function");
		return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
	}

}
