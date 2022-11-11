package com.cms.dao;

import com.cms.exceptions.LoginException;
import com.cms.model.Faculty;

public interface LoginDao {
	
	public void signInAdmin(String userName, String password) throws LoginException;
	public Faculty signInFaculty(String userName, String password) throws LoginException;

}
