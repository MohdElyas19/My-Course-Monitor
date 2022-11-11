package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.exceptions.CoursePlanException;
import com.cms.model.CoursePlan;
import com.cms.utility.DbUtil;

public class CoursePlanDaoImpl implements CoursePlanDao {

	@Override
	public String createCoursePlan(CoursePlan coursePlan) throws CoursePlanException {
		
		String result = "CoursePlan Not inserted";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("insert into CoursePlan values(?,?,?,?,?)");
			
			ps.setInt(1, coursePlan.getPlanId());
			ps.setInt(2,coursePlan.getBatchId());
			ps.setInt(3, coursePlan.getDayNumber());
			ps.setString(4, coursePlan.getTopic());
			ps.setString(5, coursePlan.getStatus());
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "CoursePlan Registered Successfully";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CoursePlanException(e.getMessage());
		}
		
		return result;
		
	}

	@Override
	public String updateCoursePlan(String topic, String status) throws CoursePlanException {

		String result = "CoursePlan Not Updated";
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("update CoursePlan set status = ? where topic = ?");
			
			ps.setString(1,status);
			ps.setString(2,topic);
			
			int x = ps.executeUpdate();
			if(x > 0) {
				result = "CoursePlan Updated Successfully";
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CoursePlanException(e.getMessage());
		}
		
		return result;

	}

	@Override
	public List<CoursePlan> viewCoursePlan() throws CoursePlanException {
		List<CoursePlan> coursePlanList = new ArrayList<>();
		
		try(Connection conn = DbUtil.provideConnection()) {
			PreparedStatement ps = conn.prepareStatement("select * from CoursePlan");
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CoursePlan coursePlan = new CoursePlan();
				coursePlan.setPlanId(rs.getInt("planId"));;
				coursePlan.setBatchId(rs.getInt("batchId"));
				coursePlan.setDayNumber(rs.getInt("dayNumber"));
				coursePlan.setTopic(rs.getString("topic"));
				coursePlan.setStatus(rs.getString("status"));
				
				coursePlanList.add(coursePlan);
			}
			
			if(coursePlanList.size() == 0) {
				throw new CoursePlanException("No data found in this batch");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CoursePlanException(e.getMessage());
		}
		
		return coursePlanList;
	}

	

}
