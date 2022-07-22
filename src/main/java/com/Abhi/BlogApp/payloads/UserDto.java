package com.Abhi.BlogApp.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4,message="username must be minimum of 4 charcters")
	private String name;
	
	@Email(message="your email Address Not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=10,message="password Must Be Minimum of 3 char and maximum of 10 chars !!! ")
	
	private String password;
	
	@NotEmpty
	private String about;
	

	private Set<RoleDto> roles = new HashSet<>();

}
