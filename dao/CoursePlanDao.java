package com.cms.dao;

import java.util.List;

import com.cms.exceptions.CoursePlanException;
import com.cms.model.CoursePlan;

public interface CoursePlanDao {
	
	public String createBatch(CoursePlan coursePlan) throws CoursePlanException;
	public String updateCourse(CoursePlan coursePlan) throws CoursePlanException;
	public List<CoursePlan> viewBatch() throws CoursePlanException;

}
