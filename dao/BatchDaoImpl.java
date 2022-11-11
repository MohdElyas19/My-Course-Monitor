package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.exceptions.BatchException;
import com.cms.model.Batch;
import com.cms.utility.DbUtil;

public class BatchDaoImpl implements BatchDao {

	@Override
	public String createBatch(Batch batch) throws BatchException {
		
		String result = "Batch Not inserted";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into Batch values(?,?,?,?,?,?)");
			
			ps.setInt(1,batch.getBatchId());
			ps.setInt(2,batch.getCourseId());
			ps.setInt(3,batch.getFacultyId());
			ps.setInt(4,batch.getNumberOfStudents());
			ps.setString(5,batch.getBatchStartDate());
			ps.setInt(6,batch.getDuration());
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "Batch Registered Successfully";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BatchException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public String updateBatch(int numberOfStudents, int courseId) throws BatchException {
		String result = "Batch Not Updated";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update Batch set numberOfStudents = ? where courseId = ?");
			
			ps.setInt(1,numberOfStudents);
			ps.setInt(2,courseId);
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "Batch Updated Successfully";
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BatchException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Batch> viewBatch() throws BatchException {
		
		List<Batch> batchList = new ArrayList<>();
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from Batch");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Batch batch = new Batch();
				batch.setBatchId(rs.getInt("batchId"));
				batch.setCourseId(rs.getInt("courseId"));
				batch.setFacultyId(rs.getInt("facultyId"));
				batch.setNumberOfStudents(rs.getInt("NumberOfStudents"));
				batch.setBatchStartDate(rs.getString("batchStartDate"));
				batch.setDuration(rs.getInt("durationInMonth"));
				
				batchList.add(batch);
			}
			
			if(batchList.size() == 0) {
				throw new BatchException("No data found in this batch");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BatchException(e.getMessage());
		}
		
		return batchList;
	}

}
