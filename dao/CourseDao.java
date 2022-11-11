package com.cms.dao;

import java.util.List;

import com.cms.exceptions.CourseException;
import com.cms.model.Course;

public interface CourseDao {
	
	public String createCourse(Course course) throws CourseException;
	
	public String updateCourse(int courseFee, String courseName) throws CourseException;
	
	public List<Course> viewCourse() throws CourseException;

}
