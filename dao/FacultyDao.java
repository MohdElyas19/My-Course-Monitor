package com.cms.dao;

import java.util.List;

import com.cms.exceptions.FacultyException;
import com.cms.model.Faculty;

public interface FacultyDao {
	
	public String createFaculty(Faculty faculty) throws FacultyException;
	public String updateFaculty(String password, String userName) throws FacultyException;
	public List<Faculty> viewFaculty() throws FacultyException;

}
