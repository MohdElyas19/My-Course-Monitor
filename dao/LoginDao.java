package com.cms.dao;

import com.cms.exceptions.LoginException;

public interface LoginDao {
	
	public String signInAdmin(String userName, String password) throws LoginException;
	public String signInFaculty(String userName, String password) throws LoginException;

}
