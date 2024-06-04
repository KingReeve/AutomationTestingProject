package com.revature.service;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

public class UserService {

	private UserDao dao;

	public UserService(UserDao dao){
		this.dao = dao;
	}

	public User authenticate(UsernamePasswordAuthentication loginRequestData) {
		// User compareUser = dao.getUserByUsername(loginRequestData.getUsername());
		
		// if(compareUser.getUsername() != null){
		// 	boolean passwordMatch = loginRequestData.getPassword().equals(compareUser.getPassword());
		// 	if(passwordMatch){
		// 		return compareUser;
		// 	}
		// }
		// return new User();

		//The password checking is handled in the controller
		return dao.getUserByUsername(loginRequestData.getUsername());
	}

	public User register(User registerRequestData) {
		String username = registerRequestData.getUsername();
		if(dao.getUserByUsername(username).getUsername() != null){
			System.out.println("\n\nThis username is already taken.  Try again!");
			return new User();
		}
		if(registerRequestData.getUsername().length() <= 30 && registerRequestData.getPassword().length() <= 30){
			User possibleUsername = dao.getUserByUsername(registerRequestData.getUsername());

			if(possibleUsername != null){
				UsernamePasswordAuthentication validCredentials = new UsernamePasswordAuthentication();
				validCredentials.setUsername(registerRequestData.getUsername());
				validCredentials.setPassword(registerRequestData.getPassword());

				return dao.createUser(validCredentials);
			}
		}
		return new User();
	}
}
