package com.cms.dao;

import java.util.List;

import com.cms.exceptions.BatchException;
import com.cms.model.Batch;

public interface BatchDao {
	
	public String createBatch(Batch batch) throws BatchException;
	public String updateBatch(int numberOfStudents, int courseId) throws BatchException;
	public List<Batch> viewBatch() throws BatchException;

}
