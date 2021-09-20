package com.revature.dao;

import java.util.List;


import com.revature.model.Admin;


public interface AdminDAO  {
	public Admin getAdminById(Long adminId);

	public List<Admin> getAdminByName(String adminName);
	
	public List<Admin> getAdminByRole(String adminRole);
	
	public boolean isAdminExists(Long adminId);

	public List<Admin> getAllAdmins();

}
