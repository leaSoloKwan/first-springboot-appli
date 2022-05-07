package com.springboot.web.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {
	public boolean validateUser(String userid, String password) {
		// FirstUser, User1
		return userid.equalsIgnoreCase("FirstUser")
				&& password.equalsIgnoreCase("User1");
	}
}
