package com.revature.librarymanagement.dto;

import java.util.Date;
import java.util.Set;

import com.revature.librarymanagement.model.IssueBook;

public class UserDto {
	private Long userId;
	private String firstName;

	private String lastName;
	private String password;
	private String mailId;
	private String mobileNumber;
	private String userRole;
	private Date createdOn;
	private Date updatedOn;
private Set<IssueBook> issueBook;

public UserDto() {
	
}


public UserDto(Long userId, String firstName, String lastName, String password, String mailId, String mobileNumber,
		String userRole, Date createdOn, Date updatedOn, Set<IssueBook> issueBook) {
	super();
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.password = password;
	this.mailId = mailId;
	this.mobileNumber = mobileNumber;
	this.userRole = userRole;
	this.createdOn = createdOn;
	this.updatedOn = updatedOn;
	this.issueBook = issueBook;
}


public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getMailId() {
	return mailId;
}

public void setMailId(String mailId) {
	this.mailId = mailId;
}

public String getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

public String getUserRole() {
	return userRole;
}

public void setUserRole(String userRole) {
	this.userRole = userRole;
}

public Date getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}

public Date getUpdatedOn() {
	return updatedOn;
}

public void setUpdatedOn(Date updatedOn) {
	this.updatedOn = updatedOn;
}

public Set<IssueBook> getIssueBook() {
	return issueBook;
}

public void setIssueBook(Set<IssueBook> issueBook) {
	this.issueBook = issueBook;
}


@Override
public String toString() {
	return "UserDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
			+ password + ", mailId=" + mailId + ", mobileNumber=" + mobileNumber + ", userRole=" + userRole
			+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", issueBook=" + issueBook + "]";
}


}
