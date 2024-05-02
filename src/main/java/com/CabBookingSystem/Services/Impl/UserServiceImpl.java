package com.CabBookingSystem.Services.Impl;

import java.util.HashMap;
import java.util.Map;

import com.CabBookingSystem.Exceptions.UserRegistrationException;
import com.CabBookingSystem.Services.UserService;
import com.CabBookingSystem.models.User;

public class UserServiceImpl implements UserService {

	public Map<String , User> users = new HashMap<>();

	@Override
	public void add_user(User user) throws UserRegistrationException {
		// TODO Auto-generated method stub
		if (isUserInvalid(user)) {
	        throw new UserRegistrationException("Invalid user information");
	    }
	    users.put(user.getUsernmae(), user);
	}
	
	
//	Utility method to check if any user field is empty or null
	private boolean isUserInvalid(User user) {
		return user == null ||
				user.getUsernmae() == null || user.getUsernmae().isEmpty() ||
				user.getGender() == null || user.getGender().isEmpty() || 
				user.getAge() == -1;
	}

}
