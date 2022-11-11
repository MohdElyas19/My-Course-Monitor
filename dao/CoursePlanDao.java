package com.cms.dao;

import java.util.List;

import com.cms.exceptions.CoursePlanException;
import com.cms.model.CoursePlan;

public interface CoursePlanDao {
	
	public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException;
	public String updateCoursePlan(String topic, String status) throws CoursePlanException;
	public List<CoursePlan> viewCoursePlan() throws CoursePlanException;

}
