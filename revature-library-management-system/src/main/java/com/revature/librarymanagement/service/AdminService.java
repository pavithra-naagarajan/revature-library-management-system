package com.revature.librarymanagement.service;

import java.util.List;

import com.revature.librarymanagement.model.Admin;


public interface AdminService {
	
	
/**
 * 
 * @param adminId --this is the primary key of admin table.
 * @return--by passing admin Id we can retrieve admin details respective to the Id.
 */
	public Admin getAdminById(Long adminId);

	public List<Admin> getAdminByName(String adminName);
	
	/**
	 * 
	 * @param adminRole--role of the admin
	 * @return--it will return the admin data respective to the role
	 */
	public List<Admin> getAdminByRole(String adminRole);
	
	/**
	 * 
	 * @param adminId
	 * @return--it will check the existence of the admin based on adminId which we passed.
	 */
	public boolean isAdminExists(Long adminId);

	/**
	 * 
	 * @return--it will return the list of admins. 
	 */
	
	public List<Admin> getAllAdmins();

}
