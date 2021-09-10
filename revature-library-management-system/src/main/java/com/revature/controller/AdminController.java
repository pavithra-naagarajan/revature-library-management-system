package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Admin;

import com.revature.service.AdminService;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	
	
	@GetMapping("role/{adminRole}")  
	 public ResponseEntity <List<Admin>> getAdminByRole(@PathVariable String  adminRole){  
		 
		 return new ResponseEntity<>(adminService.getAdminByRole(adminRole), HttpStatus.OK); 
	}
	@GetMapping("Name/{adminName}")  
	 public ResponseEntity <List<Admin>> getAdminByName(@PathVariable String  adminName){  
		 
		 return new ResponseEntity<>(adminService.getAdminByName(adminName), HttpStatus.OK); 
	}
	@GetMapping("/{adminId}")  
	 public ResponseEntity <Admin> getAdminByRole(@PathVariable Long  adminId){  
		 
		 return new ResponseEntity<>(adminService.getAdminById(adminId), HttpStatus.OK); 
	}
	@GetMapping
	public ResponseEntity<List<Admin>> getAllUsers() {
		 
		 return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK); 
	}
}
