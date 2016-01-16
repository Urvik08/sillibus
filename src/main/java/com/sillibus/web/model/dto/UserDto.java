package com.sillibus.web.model.dto;

public class UserDto {
	public String   email;
	public String   firstName;
	public long     id;
	public String   lastName;
	public String   password;
	public UserType userType;
	public String   username;

	public UserDto () {
	}

	public UserDto (long id, String email, String firstName, String lastName, String password, UserType userType, String username) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userType = userType;
		this.username = username;
	}
}
