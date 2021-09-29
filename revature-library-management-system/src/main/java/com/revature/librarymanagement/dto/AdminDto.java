package com.revature.librarymanagement.dto;

public class AdminDto {
	private Long adminId;
	private String adminName;
	private String adminRole;
	private String adminPassword;
	private String mailId;
	
	public AdminDto() {
		
	}
	

	public AdminDto(Long adminId, String adminName, String adminRole, String adminPassword, String mailId) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.adminPassword = adminPassword;
		this.mailId = mailId;
	}


	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
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

	@Override
	public String toString() {
		return "AdminDto [adminId=" + adminId + ", adminName=" + adminName + ", adminRole=" + adminRole
				+ ", adminPassword=" + adminPassword + ", mailId=" + mailId + "]";
	}

}
