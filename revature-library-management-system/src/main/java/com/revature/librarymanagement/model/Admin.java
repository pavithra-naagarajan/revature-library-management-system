package com.revature.librarymanagement.model;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="admins_table")


public class Admin {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long adminId;
	
	@Column(nullable=false)
	private String adminName;
	
	@Column(nullable=false)
	private String adminRole;
	
	@Column(nullable=false,unique=true)
	private String mailId;
	
	@Column(nullable=false)
	private String adminPassword;
	
	public Admin() {
		
	}

	
	public Admin(Long adminId, String adminName, String adminRole, String mailId, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.mailId = mailId;
		this.adminPassword = adminPassword;
	}


	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getMailId() {
		return mailId;
	}


	public void setMailId(String mailId) {
		this.mailId = mailId;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminRole=" + adminRole + ", mailId="
				+ mailId + ", adminPassword=" + adminPassword + "]";
	}


	

}
