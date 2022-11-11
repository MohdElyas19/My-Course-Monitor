package com.cms.dao;

import java.util.List;

import com.cms.exceptions.FacultyException;
import com.cms.model.Faculty;

public interface FacultyDao {
	
	public String createBatch(Faculty faculty) throws FacultyException;
	public String updateCourse(Faculty faculty) throws FacultyException;
	public List<Faculty> viewBatch() throws FacultyException;

}
