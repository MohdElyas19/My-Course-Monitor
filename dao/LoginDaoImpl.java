package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cms.exceptions.LoginException;
import com.cms.model.Faculty;
import com.cms.utility.DbUtil;

public class LoginDaoImpl implements LoginDao {

	@Override
	public void signInAdmin(String userName, String password) throws LoginException {
		
		if(userName.equals("Iliyas") && password.equals("iliyas123")) {
//			System.out.println("Login successful");
			System.out.println("	------- Welcome Mr/Mrs "+userName.toUpperCase()+" to the COURSE MONITORING SYSTEM -------");
		}
		else {
			throw new LoginException("Invalid userName or password");
		}
		
	}

	@Override
	public Faculty signInFaculty(String userName, String password) throws LoginException {
		Faculty faculty = null;
		
		try(Connection conn = DbUtil.provideConnection()) {
			
			PreparedStatement ps = conn.prepareStatement("select * from Faculty where userName = ? AND password = ?");
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int facId = rs.getInt("facultyId");
				String facName = rs.getString("facultyName");
				String facAdd = rs.getString("facultyAddress");
				String mob = rs.getString("mobile");
				String email = rs.getString("email");
				String userNmae = rs.getString("userName");
				String pass = rs.getString("password");
				
				faculty = new Faculty(facId, facName, facAdd, mob, email, userName, password);
				
			}
			else {
				throw new LoginException("Invalid Username or Password");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException(e.getMessage());
		}
		
		return faculty;
	}

}
