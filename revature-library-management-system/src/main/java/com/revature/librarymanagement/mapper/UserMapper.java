package com.revature.librarymanagement.mapper;

import com.revature.librarymanagement.dto.UserDto;
import com.revature.librarymanagement.model.User;

public class UserMapper {

	//dto to entity mapping
	public static User dtoToEntity(UserDto userDto)
	{
		User user=new User();
		user.setUserId(userDto.getUserId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setPassword(userDto.getPassword());
		user.setMailId(userDto.getMailId());
		user.setMobileNumber(userDto.getMobileNumber());
		user.setUserRole(userDto.getUserRole());
		user.setCreatedOn(userDto.getCreatedOn());
		user.setUpdatedOn(userDto.getUpdatedOn());
		user.setIssueBook(userDto.getIssueBook());
		
		return user;
		
	}

}
