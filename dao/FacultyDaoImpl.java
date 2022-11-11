package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.exceptions.FacultyException;
import com.cms.model.Faculty;
import com.cms.utility.DbUtil;

public class FacultyDaoImpl implements FacultyDao {

	@Override
	public String createFaculty(Faculty faculty) throws FacultyException {
		
		String result = "Faculty Not inserted";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into Faculty values(?,?,?,?,?,?,?)");
			
			ps.setInt(1,faculty.getFacultyId());
			ps.setString(2,faculty.getFacultyName());
			ps.setString(3,faculty.getFacultyAddress());
			ps.setString(4,faculty.getMobile());
			ps.setString(5,faculty.getEmail());
			ps.setString(6,faculty.getUserName());
			ps.setString(7,faculty.getPassword());
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "Faculty Registered Successfully";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacultyException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public String updateFaculty(String password, String userName) throws FacultyException {
		
		String result = "Faculty Not Updated";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update Faculty set password = ? where userName = ?");
			
			ps.setString(1,password);
			ps.setString(2,userName);
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "Faculty Updated Successfully";
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacultyException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Faculty> viewFaculty() throws FacultyException {
		
		List<Faculty> facultyList = new ArrayList<>();
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from Faculty");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Faculty faculty = new Faculty();
				faculty.setFacultyId(rs.getInt("facultyId"));
				faculty.setFacultyName(rs.getString("facultyName"));
				faculty.setFacultyAddress(rs.getString("facultyAddress"));
				faculty.setMobile(rs.getString("mobile"));
				faculty.setEmail(rs.getString("email"));
				faculty.setUserName(rs.getString("userName"));
				faculty.setPassword(rs.getString("password"));
				
				facultyList.add(faculty);
			}
			
			if(facultyList.size() == 0) {
				throw new FacultyException("No data found in this faculty");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new FacultyException(e.getMessage());
		}
		
		return facultyList;
	}

}
