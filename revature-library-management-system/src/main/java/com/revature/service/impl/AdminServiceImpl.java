package com.revature.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dao.AdminDAO;
import com.revature.model.Admin;

import com.revature.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService  {

	
	
	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public Admin getAdminById(Long adminId) {
		return adminDAO.getAdminById(adminId);
		
	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		return adminDAO.getAdminByName(adminName);
		
	}

	@Override
	public List<Admin> getAdminByRole(String adminRole) {
		return adminDAO.getAdminByRole(adminRole);
	}

	@Override
	public boolean isAdminExists(Long adminId) {
		Admin adminData = adminDAO.getAdminById(adminId);
		return (adminData!=null);
	}

	@Override
	public List<Admin> getAllAdmins() {
		return  adminDAO.getAllAdmins();
	}

}
